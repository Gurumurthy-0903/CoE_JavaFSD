package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static final String URL = "jdbc:mysql://localhost:3306/feereport";
    private static final String USER = "root"; // Change if you have a different username
    private static final String PASSWORD = "p@$$W0rd"; // Replace with your MySQL password

    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) // For testing only
    {
        Connection conn = getConnection();
        if (conn != null)
            System.out.println("Database Connected Successfully!");
        else
            System.out.println("Failed to Connect.");
    }
}
