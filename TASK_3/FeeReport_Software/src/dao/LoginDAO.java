package dao;
import java.sql.*;
import util.DatabaseConnection;

public class LoginDAO
{
    public static boolean validateAdmin(String username, String password)
    {
        String query = "SELECT * FROM admin WHERE username=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query))
        {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Returns true if user exists
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateAccountant(String email, String password)
    {
        String query = "SELECT * FROM accountant WHERE email=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query))
        {
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Returns true if user exists
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
