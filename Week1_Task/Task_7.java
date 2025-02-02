//Task_7
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Task_7
{

    public static List<Integer> findAnagrams(String s, String p)
    {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || p == null || s.length() < p.length())
        {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (char c : p.toCharArray())
        {
            pCount[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++)
        {
            sCount[s.charAt(i) - 'a']++;

            if (i >= p.length())
            {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            if (i >= p.length() - 1 && areArraysEqual(sCount, pCount))
            {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    private static boolean areArraysEqual(int[] arr1, int[] arr2)
    {
        for (int i = 0; i < 26; i++)
        {
            if (arr1[i] != arr2[i])
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        String defaultS = "cbaebabacd";
        String defaultP = "abc";
        System.out.println("Default output: " + findAnagrams(defaultS, defaultP));  // Output: [0, 6]

        // User input implementation
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string s: ");
        String s = scanner.nextLine();
        System.out.print("Enter string p: ");
        String p = scanner.nextLine();
        System.out.println("Anagrams found at indices: " + findAnagrams(s, p));
        scanner.close();
        */
    }
}
