
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class LetterCounter {
    public static void main(String[] args) throws Exception {

        char[] letters = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();
        int[] count = new int[letters.length];

        System.out.print("enter a file to read: ");
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();
        BufferedReader br = new BufferedReader(new FileReader(file));

        int currentCharInt;
        while ((currentCharInt = br.read()) != -1) {
            for (int i = 0; i < letters.length; i++) {
                char currentChar = (char) currentCharInt;
                if (currentChar == letters[i]) {
                    count[i]++;
                }
            }
        }
        for (int i = 0; i < letters.length; i++) {
            System.out.printf("number of %c's: %d\n", letters[i], count[i]);
        }
        br.close();
        sc.close();
    }
}
