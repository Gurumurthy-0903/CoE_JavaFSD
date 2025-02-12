import java.util.Scanner;
import dao.LoginDAO;

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
}
