//Task_4
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User
{
    private String name;
    private String email;

    public User(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return name + "," + email;
    }

    public static User fromString(String data)
    {
        String[] parts = data.split(",");
        return new User(parts[0], parts[1]);
    }
}

class UserManager
{
    private List<User> users;

    public UserManager()
    {
        users = new ArrayList<>();
    }

    public void addUser(String name, String email)
    {
        users.add(new User(name, email));
    }

    public void saveUsersToFile(String filename)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename)))
        {
            for (User user : users)
            {
                writer.write(user.toString());
                writer.newLine();
            }
            System.out.println("Users saved to file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile(String filename)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                users.add(User.fromString(line));
            }
            System.out.println("Users loaded from file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void displayUsers()
    {
        for (User user : users)
        {
            System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }
}

public class Task_4
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        System.out.print("Enter the file name (e.g., users.txt): ");
        String filename = scanner.nextLine();
        String filePath = "G:\\Week_1\\Task_4\\" + filename;

        System.out.print("How many users would you like to add? ");
        int numUsers = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numUsers; i++)
        {
            System.out.println("Enter details for user " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            userManager.addUser(name, email);
        }

        userManager.saveUsersToFile(filePath);

        UserManager newUserManager = new UserManager();
        newUserManager.loadUsersFromFile(filePath);

        newUserManager.displayUsers();

        scanner.close();
    }
}
