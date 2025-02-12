import java.util.Scanner;
import dao.LoginDAO;
import dao.StudentDAO;
import model.Student;

public class MainMenu
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("\n===== Fee Report System =====");
            System.out.println("1. Admin Login");
            System.out.println("2. Accountant Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice)
            {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    accountantLogin(scanner);
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void adminLogin(Scanner scanner)
    {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (LoginDAO.validateAdmin(username, password))
        {
            System.out.println("Admin Login Successful!");
            AdminMenu.showAdminMenu(scanner); // Call Admin Menu
        }
        else
        {
            System.out.println("Invalid Admin Credentials!");
        }
    }


    private static void accountantLogin(Scanner scanner)
    {
        System.out.print("Enter Accountant Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (LoginDAO.validateAccountant(email, password))
        {
            System.out.println("Accountant Login Successful!");
            AccountantMenu.showMenu(); // Call Accountant Menu
        }
        else
        {
            System.out.println("Invalid Accountant Credentials!");
        }
    }


    private static void accountantMenu(Scanner scanner)
    {
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
            scanner.nextLine();  // Consume newline

            switch (choice)
            {
            case 1:
                System.out.println("Adding Student...");
                Student newStudent = new Student("John Doe", "john@example.com", "CS", 50000, 30000, 20000, "NYC", "1234567890");
                boolean added = StudentDAO.addStudent(newStudent);
                System.out.println(added ? "Student Added!" : "Failed to Add Student!");
                break;

                case 2:
                    System.out.println("Feature: View Students (To be implemented)");
                    break;
                case 3:
                    System.out.println("Feature: Edit Student (To be implemented)");
                    break;
                case 4:
                    System.out.println("Feature: Delete Student (To be implemented)");
                    break;
                case 5:
                    System.out.println("Feature: Check Due Fees (To be implemented)");
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

}
