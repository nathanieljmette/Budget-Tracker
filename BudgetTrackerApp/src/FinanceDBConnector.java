import java.sql.*;
import java.util.HashSet;

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

    public String[] collectUser(String username, String password) throws SQLException {
        Statement stmt = conn.createStatement();
        String encryptPassword = password;
        ResultSet results = stmt.executeQuery(
            "SELECT [username],[password],[fname],[lname],[monthlyincome],[activebudget] FROM [dbo].[User]" +
            "WHERE [username] = " + username + " AND [password] = " + encryptPassword + ";");

        String[] retval = new String[0];
        if (results.next()) {
            retval = new String[6];
            retval[0] = results.getString(0);
            retval[1] = results.getString(1);
            retval[2] = results.getString(2);
            retval[3] = results.getString(3);
            retval[4] = Double.toString(results.getDouble(4));
            retval[5] = results.getString(5);
        }
        stmt.close();
        return retval;
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
    

}
