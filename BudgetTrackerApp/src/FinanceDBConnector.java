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

        this.conn = DriverManager.getConnection(connectionURl, "nmette", "123");
    }

    public void close() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }

    public HashSet<String> getBudgetNames(String username) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT DISTINCT [budgetname] FROM [dbo].[UserExpense] WHERE [username]=" + username + ";");
        HashSet<String> names = new HashSet<>();
        while (results.next()) {
            names.add(results.getString("budgetname"));
        }
        return names;
    }

    public User collectUser(String username, String password) throws SQLException {
        Statement stmt = conn.createStatement();
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
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [ExpenseID],[ExpenseName] FROM [dbo].[UserExpense] WHERE [budgetname]=" + budgetName + " AND [username]=" + username + ";");
        
        ArrayList<String[]> expenseIDs = new ArrayList<>();
        while (results.next()) {
            String[] temp = new String[2];
            temp[0] = results.getString("ExpenseID");
            temp[1] = results.getString("ExpenseName");
            expenseIDs.add(temp);
        }
        stmt.close();

        Budget budget = new Budget()
            .withBudgetName(budgetName)
            .withUsername(username);
        
        for (String[] expense : expenseIDs) {
            Expense expenseObj = getExpense(expense[0]);
            if (expenseObj != null) {
                expenseObj.withExpenseName(expense[1]);
                budget.withExpense(expenseObj);
            }
        }
        return budget;
    }

    public Budget getActiveBudget(String budgetName, String username) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [ExpenseID],[ExpenseName] FROM [dbo].[UserExpense] WHERE [budgetname]=" + budgetName + " AND [username]=" + username + ";");
        
        ArrayList<String[]> expenseIDs = new ArrayList<>();
        while (results.next()) {
            String[] temp = new String[2];
            temp[0] = results.getString("ExpenseID");
            temp[1] = results.getString("ExpenseName");
            expenseIDs.add(temp);
        }
        stmt.close();

        Budget budget = new Budget()
            .withBudgetName(budgetName)
            .withUsername(username);
        
        for (String[] expense : expenseIDs) {
            Expense expenseObj = getExpense(expense[0]);
            if (expenseObj != null) {
                expenseObj = updateToActiveExpense(expenseObj);
                expenseObj.withExpenseName(expense[1]).withBelongsTo(username);
                budget.withExpense(expenseObj);
            }
        }
        return budget;
    }

    private Expense getExpense(String expenseID) throws SQLException {
        Statement stmt = conn.createStatement();
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
        stmt.close();
        return expense;
    }

    private Expense updateToActiveExpense(Expense expense) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(
            "SELECT [Spent],[DateBegan] FROM [dbo].[UserExpense] WHERE [ExpenseID]=" + expense.getExpenseID() + ";");
        if (results.next()) {
            expense.withSpent(results.getDouble("Spent"))
                .withDateBegan(results.getDate("DateBegan"));
        }
        stmt.close();
        return expense;
    }
    

}
