import java.sql.*;
import java.util.HashSet;
import java.util.ArrayList;

public class FinanceDBConnector {

    final String connectionURl = "jdbc:sqlserver://localhost;trustServerCertificate=true;database=Finance";
    Connection conn = null;

    public FinanceDBConnector() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(ClassNotFoundException err) {
            System.err.println("Exception occurred: '''" + err + "'''");
        }

        this.conn = DriverManager.getConnection(this.connectionURl, "nmette", "123");
        this.conn.setAutoCommit(false);
    }

    public void close() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }

    public HashSet<String> getBudgetNames(String username) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT DISTINCT [budgetname] FROM [dbo].[UserExpense] WHERE [username]=" + username + ";");
        HashSet<String> names = new HashSet<>();
        while (results.next()) {
            names.add(results.getString("budgetname"));
        }
        return names;
    }

    public User collectUser(String username, String password) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        String encryptPassword = password;
        ResultSet results = stmt.executeQuery(
            "SELECT [fname],[lname],[monthlyincome],[activebudget] FROM [dbo].[User]" +
            "WHERE [username]=" + username + " AND [password]=" + encryptPassword + ";");

        User user = null;
        if (results.next()) {
            user = new User();
            user.withUsername(username)
                .withPassword(encryptPassword)
                .withName(results.getString("fname"), results.getString("lname"))
                .withMonthlyIncome(results.getDouble("monthlyincome"))
                .withActiveBudget(results.getString("activebudget"));
        }
        stmt.close();
        return user;
    }

    public Budget getBudgetPrefab(String budgetName, String username) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [ExpenseID],[ExpenseName] FROM [dbo].[UserExpense] WHERE [budgetname]=" + budgetName + " AND [username]=" + username + ";");

        if (!results.isBeforeFirst()) {
            stmt.close();
            return null; // No expenses found for the given budget and username
        }
        ArrayList<String[]> expenseIDs = new ArrayList<>();
        while (results.next()) {
            String[] temp = new String[2];
            temp[0] = results.getString("ExpenseID");
            temp[1] = results.getString("ExpenseName");
            expenseIDs.add(temp);
        }

        Budget budget = new Budget()
            .withBudgetName(budgetName)
            .withUsername(username);
        
        for (String[] expense : expenseIDs) {
            Expense expenseObj = getExpense(stmt, expense[0]);
            if (expenseObj != null) {
                expenseObj.withExpenseName(expense[1]);
                budget.withExpense(expenseObj);
            }
        }

        stmt.close();
        return budget;
    }

    public void getActiveBudget(Budget budget) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        for (Expense expense : budget.getExpenses()) {
            expense = getActiveExpense(stmt, expense);
        }
        
        stmt.close();
    }

    public Budget getActiveBudget(String budgetName, String username) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [ExpenseID],[ExpenseName] FROM [dbo].[UserExpense] WHERE [budgetname]=" + budgetName + " AND [username]=" + username + ";");
        
        if (!results.isBeforeFirst()) {
            stmt.close();
            return null; // No expenses found for the given budget and username
        }
        ArrayList<String[]> expenseIDs = new ArrayList<>();
        while (results.next()) {
            String[] temp = new String[2];
            temp[0] = results.getString("ExpenseID");
            temp[1] = results.getString("ExpenseName");
            expenseIDs.add(temp);
        }

        Budget budget = new Budget()
            .withBudgetName(budgetName)
            .withUsername(username);
        
        for (String[] expense : expenseIDs) {
            Expense expenseObj = getActiveExpense(stmt, expense[0]);
            expenseObj.withExpenseName(expense[1]); //.withBelongsTo(username);
            budget.withExpense(expenseObj);
        }
        stmt.close();
        return budget;
    }

    public ArrayList<Transaction> getTransactionsForBudget(Budget budget) throws SQLException {
        ArrayList<Transaction> allTransactions = new ArrayList<>();
        for (Expense expense : budget.getExpenses()) {
            allTransactions.addAll(getTransactionsForExpense(expense));
        }
        return allTransactions;
    }

    public ArrayList<Transaction> getTransactionsForExpense(Expense expense) throws SQLException {
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("Connection is not established.");
        }
        Statement stmt = this.conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [XactID],[Date],[Spent],[Name],[Notes] FROM [dbo].[Transaction] WHERE [ExpenseID]=" + expense.getExpenseID() + " ORDER BY [Date];");
        
        ArrayList<Transaction> transactions = new ArrayList<>();
        while (results.next()) {
            Transaction transaction = new Transaction()
                .withXactID(results.getString("XactID"))
                .withExpenseID(expense.getExpenseID())
                .withDate(results.getDate("Date"))
                .withAmount(results.getDouble("Spent"))
                .withXactName(results.getString("Name"))
                .withNotes(results.getString("Notes"));
            transactions.add(transaction);
        }

        stmt.close();
        expense.withTransactions(transactions);
        return transactions;
    }

    private Expense getExpense(Statement stmt, String expenseID) throws SQLException {
        ResultSet results = stmt.executeQuery(
            "SELECT [PriorityLevel],[Commitment],[Static] FROM [dbo].[ExpensePrefab] WHERE [ExpenseID]=" + expenseID + ";");
        Expense expense = null;
        if (results.next()) {
            expense = new Expense()
                .withExpenseID(expenseID)
                .withPriorityLevel(results.getInt("PriorityLevel"))
                .withCommitment(results.getDouble("Commitment"))
                .withIsStatic(results.getBoolean("Static"));
        }
        return expense;
    }

    private Expense getActiveExpense(Statement stmt, Expense expense) throws SQLException {
        ResultSet results = stmt.executeQuery(
            "SELECT [Spent],[DateBegan] FROM [dbo].[UserExpense] WHERE [ExpenseID]=" + expense.getExpenseID() + ";");
        if (results.next()) {
            expense.withSpent(results.getDouble("Spent"))
                .withDateBegan(results.getDate("DateBegan"));
        }
        return expense;
    }

    private Expense getActiveExpense(Statement stmt, String expenseID) throws SQLException {
        ResultSet results = stmt.executeQuery(
            "SELECT [PriorityLevel],[Commitment],[Static],[Spent],[DateBegan] FROM [dbo].[ExpensePrefab],[dbo].[UserExpense]"
             + "WHERE [dbo].[ExpensePrefab].[ExpenseID]=[dbo].[UserExpense].[ExpenseID] AND [dbo].[UserExpense].[ExpenseID]=" + expenseID + ";");
        Expense expense = null;
        if (results.next()) {
            expense = new Expense()
                .withExpenseID(expenseID)
                .withPriorityLevel(results.getInt("PriorityLevel"))
                .withCommitment(results.getDouble("Commitment"))
                .withIsStatic(results.getBoolean("Static"))
                .withSpent(results.getDouble("Spent"))
                .withDateBegan(results.getDate("DateBegan"));
        }
        return expense;
    }
    

}
