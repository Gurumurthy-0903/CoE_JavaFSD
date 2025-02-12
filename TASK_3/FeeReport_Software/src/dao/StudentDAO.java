package dao;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class StudentDAO
{
	public static boolean addStudent(Student student)
	{
	    String checkQuery = "SELECT COUNT(*) FROM student WHERE email = ?";
	    String insertQuery = "INSERT INTO student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	         PreparedStatement insertStmt = conn.prepareStatement(insertQuery))
	    {
	        checkStmt.setString(1, student.getEmail());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        if (rs.getInt(1) > 0)
	        {
	            System.out.println("Student with this email already exists!");
	            return false;
	        }

	        // Insert new student
	        insertStmt.setString(1, student.getName());
	        insertStmt.setString(2, student.getEmail());
	        insertStmt.setString(3, student.getCourse());
	        insertStmt.setDouble(4, student.getFee());
	        insertStmt.setDouble(5, student.getPaid());
	        insertStmt.setDouble(6, student.getDue());
	        insertStmt.setString(7, student.getAddress());
	        insertStmt.setString(8, student.getPhone());

	        return insertStmt.executeUpdate() > 0;
	    }
	    catch (SQLException e)
	    {
	        e.printStackTrace();
	        return false;
	    }
	}


    public static List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getDouble("fee"),
                    rs.getDouble("paid"),
                    rs.getDouble("due"),
                    rs.getString("address"),
                    rs.getString("phone")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }

    public static boolean updateStudent(Student student)
    {
        String query = "UPDATE student SET name=?, email=?, course=?, fee=?, paid=?, due=?, address=?, phone=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setDouble(4, student.getFee());
            stmt.setDouble(5, student.getPaid());
            stmt.setDouble(6, student.getDue());
            stmt.setString(7, student.getAddress());
            stmt.setString(8, student.getPhone());
            stmt.setInt(9, student.getId());

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteStudent(int id)
    {
        String query = "DELETE FROM student WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Student> getDueFeeStudents()
    {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student WHERE due > 0";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next())
            {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getDouble("fee"),
                    rs.getDouble("paid"),
                    rs.getDouble("due"),
                    rs.getString("address"),
                    rs.getString("phone")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }
}
