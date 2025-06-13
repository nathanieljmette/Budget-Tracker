import java.sql.*;
import java.util.Properties;

public class databaseConnectionTest {

    public static void main(String[] args) {
        final String connectionUrl = "jdbc:sqlserver://localhost;trustServerCertificate=true;userName=nmette;password=123";
        //Only needed if it's listening to an IPv6 port
        //System.setProperty("java.net.preferIPv6Addresses", "true")
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("First part passed.");

            Connection con = DriverManager.getConnection(connectionUrl, new Properties());
            System.out.println("Connected!");
            
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("USE [Finance]; \n" +
                            "SELECT [username],[password],[fname],[lname],[monthlyincome],[activebudget] FROM [dbo].[User];");
            
            while (results.next()) {
                System.out.println(results.getString("username"));
                System.out.println(results.getString("password"));
                System.out.println(results.getString("fname"));
                System.out.println(results.getString("lname"));
                System.out.println(results.getDouble("monthlyincome"));
                System.out.println(results.getString("activebudget"));
            }

            con.close();
            System.out.println("Closed!");
        } catch(ClassNotFoundException err) {
            System.err.println(err);
        } catch(SQLException err) {
            System.err.println(err);
        }
    }

}
