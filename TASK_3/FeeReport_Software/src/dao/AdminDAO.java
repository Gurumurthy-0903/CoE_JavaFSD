package dao;
import java.sql.*;
import util.DatabaseConnection;
import model.Accountant;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO
{
    public static boolean addAccountant(Accountant acc)
    {
        String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query))
        {
            pst.setString(1, acc.getName());
            pst.setString(2, acc.getEmail());
            pst.setString(3, acc.getPhone());
            pst.setString(4, acc.getPassword());

            return pst.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Accountant> getAllAccountants()
    {
        List<Accountant> accountants = new ArrayList<>();
        String query = "SELECT * FROM accountant";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                Accountant acc = new Accountant(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"), rs.getString("password"));
                accountants.add(acc);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return accountants;
    }

    public static boolean deleteAccountant(int id)
    {
        String query = "DELETE FROM accountant WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query))
        {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
