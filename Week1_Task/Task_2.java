//Task_2
import java.util.*;
class BankAccount 
{
    private double balance;

    public BankAccount(double balance) 
    {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) 
    {
        if (amount > 0) 
        {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " Deposited: " + amount + " | Balance: " + balance);
        }
        else 
        {
            System.out.println(Thread.currentThread().getName() + " Invalid deposit amount.");
        }
    }

    public synchronized void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " Withdrew: " + amount + " | Balance: " + balance);
        }
        else 
        {
            System.out.println(Thread.currentThread().getName() + " Insufficient funds for withdrawal of: " + amount);
        }
    }

    public double getBalance() 
    {
        return balance;
    }
}

class UserThread extends Thread 
{
    private BankAccount account;
    private boolean deposit;
    private double amount;

    public UserThread(BankAccount account, boolean deposit, double amount, String name) 
    {
        super(name);
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    @Override
    public void run() 
    {
        if (deposit) 
        {
            account.deposit(amount);
        }
        else 
        {
            account.withdraw(amount);
        }
    }
}

public class Task_2 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000);

        System.out.print("Enter the number of users: ");
        int numUsers = scanner.nextInt();
        scanner.nextLine();

        Thread[] users = new Thread[numUsers];

        for (int i = 0; i < numUsers; i++) 
        {
            System.out.print("Enter user name: ");
            String name = scanner.nextLine();

            System.out.print(name + ", do you want to deposit or withdraw? (d/w): ");
            char choice = scanner.next().charAt(0);

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            boolean isDeposit = (choice == 'd' || choice == 'D');
            users[i] = new UserThread(account, isDeposit, amount, name);
        }

        for (Thread user : users) 
        {
            user.start();
        }

        for (Thread user : users) 
        {
            try 
            {
                user.join();
            }
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        System.out.println("Final Account Balance: " + account.getBalance());
        scanner.close();
    }
}
