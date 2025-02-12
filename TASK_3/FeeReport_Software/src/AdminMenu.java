import java.util.List;
import java.util.Scanner;
import dao.AccountantDAO;
import model.Accountant;

public class AdminMenu
{
    public static void showAdminMenu(Scanner scanner)
    {
        while (true)
        {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. Add Accountant");
            System.out.println("2. View Accountants");
            System.out.println("3. Delete Accountant");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice)
            {
                case 1:
                    addAccountant(scanner);
                    break;
                case 2:
                    viewAccountants();
                    break;
                case 3:
                    deleteAccountant(scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; // Return to MainMenu
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addAccountant(Scanner scanner)
    {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean success = AccountantDAO.addAccountant(new Accountant(name, email, phone, password));
        System.out.println(success ? "Accountant added successfully!" : "Failed to add accountant.");
    }

    private static void viewAccountants()
    {
        List<Accountant> accountants = AccountantDAO.getAllAccountants();
        System.out.println("\n===== List of Accountants =====");
        for (Accountant acc : accountants)
        {
            System.out.println(acc);
        }
    }

    private static void deleteAccountant(Scanner scanner)
    {
        System.out.print("Enter Accountant ID to delete: ");
        int id = scanner.nextInt();

        boolean success = AccountantDAO.deleteAccountant(id);
        System.out.println(success ? "Accountant deleted successfully!" : "Failed to delete accountant.");
    }
}
