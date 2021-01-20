
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Hangman {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        BufferedReader br;
        BufferedWriter bw;
        Vector<String> tries = new Vector<>();
        Random rnd = new Random();
        int misses = 0;

        System.out.print("enter a filename: ");
        String file = sc.nextLine();
        br = new BufferedReader(new FileReader(file));
        Vector<String> words = new Vector<>();
        String line;
        while ((line = br.readLine()) != null) {
            words.add(line);
        }
        br.close();

        String answer = words.get(rnd.nextInt(words.size()));
        String hiddenAns = new String();
        for (int i = 0; i < answer.length(); i++) {
            hiddenAns += '*';
        }

        while (hiddenAns.indexOf("*") != -1) {
            System.out.printf("guess a letter in %s > ", hiddenAns);
            String guess = sc.next();
            if (tries.isEmpty()) {
                tries.add(guess);
                if (answer.indexOf(guess) != -1) {
                    String copy = hiddenAns;
                    hiddenAns = "";
                    for (int i = 0; i < answer.length(); i++) {
                        if (answer.charAt(i) == guess.charAt(0)) {
                            hiddenAns += answer.charAt(i);
                        } else {
                            hiddenAns += copy.charAt(i);
                        }
                    }
                } else {
                    misses++;
                    System.out.println(guess + " is not in the word");
                }
            } else {
                boolean flag = false;
                for (String s : tries) {
                    if (s.equals(guess)) {
                        flag = true;
                    }
                }
                if (flag) {
                    System.out.println("already guessed " + guess);
                } else {
                    tries.add(guess);
                    if (answer.indexOf(guess) != -1) {
                        String copy = hiddenAns;
                        hiddenAns = "";
                        for (int i = 0; i < answer.length(); i++) {
                            if (answer.charAt(i) == guess.charAt(0)) {
                                hiddenAns += answer.charAt(i);
                            } else {
                                hiddenAns += copy.charAt(i);
                            }
                        }
                    } else {
                        misses++;
                        System.out.println(guess + " is not in the word");
                    }
                }
            }
        }

        System.out.printf("congrats, you got the word %s with %s %s\n", answer, misses,
                (misses == 1 ? "miss" : "misses"));
        System.out.print("enter a new word to add to the word bank: ");
        String word = "";
        word += sc.nextLine();
        word += sc.nextLine();
        bw = new BufferedWriter(new FileWriter(file, true));
        bw.write(word + "\n");
        bw.close();
        sc.close();
    }
}
