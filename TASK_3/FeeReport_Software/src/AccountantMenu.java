import java.util.Scanner;
import dao.StudentDAO;
import model.Student;
import java.util.List;

public class AccountantMenu
{
    public static void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        
        while (true)
        {
            System.out.println("\n===== Accountant Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Check Due Fees");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice)
            {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    editStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    checkDueFees();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return; // Go back to Main Menu
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner)
    {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter Total Fee: ");
        double fee = scanner.nextDouble();
        System.out.print("Enter Amount Paid: ");
        double paid = scanner.nextDouble();
        double due = fee - paid;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Student student = new Student(name, email, course, fee, paid, due, address, phone);
        if (StudentDAO.addStudent(student))
            System.out.println("Student Added Successfully!");
        else
            System.out.println("Failed to Add Student!");
    }

    private static void viewStudents()
    {
        List<Student> students = StudentDAO.getAllStudents();
        System.out.println("\n===== Student Records =====");
        for (Student s : students)
        {
            System.out.println(s.getId() + " | " + s.getName() + " | " + s.getEmail() + " | Due: " + s.getDue());
        }
    }

    private static void editStudent(Scanner scanner)
    {
        System.out.print("Enter Student ID to Edit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Course: ");
        String course = scanner.nextLine();
        System.out.print("Enter New Fee: ");
        double fee = scanner.nextDouble();
        System.out.print("Enter New Amount Paid: ");
        double paid = scanner.nextDouble();
        double due = fee - paid;
        scanner.nextLine(); // Consume newline
        System.out.print("Enter New Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine();

        Student student = new Student(id, name, email, course, fee, paid, due, address, phone);
        if (StudentDAO.updateStudent(student))
            System.out.println("Student Updated Successfully!");
        else
            System.out.println("Failed to Update Student!");
    }

    private static void deleteStudent(Scanner scanner)
    {
        System.out.print("Enter Student ID to Delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (StudentDAO.deleteStudent(id))
            System.out.println("Student Deleted Successfully!");
        else
            System.out.println("Failed to Delete Student!");
    }

    private static void checkDueFees()
    {
        List<Student> dueStudents = StudentDAO.getDueFeeStudents();
        System.out.println("\n===== Students with Due Fees =====");
        for (Student s : dueStudents)
        {
            System.out.println(s.getName() + " | Due: " + s.getDue());
        }
    }
}
