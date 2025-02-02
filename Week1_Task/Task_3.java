//Task_3
import java.util.Scanner;
public class Task_3 
{
    public static void processInput() 
    {
        Scanner scanner = new Scanner(System.in);
        try 
        {
            System.out.print("Enter a number: ");
            double number = Double.parseDouble(scanner.nextLine());
            if (number == 0) 
            {
                throw new ArithmeticException("Division by zero");
            }
            double reciprocal = 1 / number;
            System.out.println("Reciprocal: " + reciprocal);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid input! Please enter a valid number.");
        }
         catch (ArithmeticException e) 
         {
            System.out.println("Division by zero is not allowed.");
         }
         finally 
         {
            scanner.close();
         }
    }
    
    public static void main(String[] args) 
    {
        processInput();
    }
}
