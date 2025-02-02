//Task_5
import java.util.Scanner;
public class Task_5
{
    public String reverseString(String str)
    {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    public int countOccurrences(String text, String sub)
    {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1)
        {
            count++;
            index += sub.length();
        }
        return count;
    }

    public String splitAndCapitalize(String str)
    {
        String[] words = str.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words)
        {
            result.append(word.substring(0, 1).toUpperCase())
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Task_5 processor = new Task_5();

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("Reversed String: " + processor.reverseString(input));

        System.out.print("Enter a substring to count occurrences: ");
        String substring = scanner.nextLine();
        System.out.println("Occurrences: " + processor.countOccurrences(input, substring));

        System.out.println("Capitalized String: " + processor.splitAndCapitalize(input));

        scanner.close();
    }
}
