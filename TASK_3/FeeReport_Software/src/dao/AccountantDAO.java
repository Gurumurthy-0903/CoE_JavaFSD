package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Accountant;
import util.DatabaseConnection;

public class AccountantDAO
{
    public static boolean addAccountant(Accountant accountant)
    {
        String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query))
        {
            pst.setString(1, accountant.getName());
            pst.setString(2, accountant.getEmail());
            pst.setString(3, accountant.getPhone());
            pst.setString(4, accountant.getPassword());
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
                accountants.add(new Accountant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("password")
                ));
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
