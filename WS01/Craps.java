
// import random utility for random number generation

import java.util.Random;

// public class needed as java is pure object oriented
public class Craps {

    // private print function for easier coding
    private static void print(String str) {
        System.out.print(str);
    }

    // private function for returning a random number between 1-6 inclusive
    private static int dieRoll() {

        // create random object for use
        Random rnd = new Random();

        // use nextInt random member function to grab a single integer
        // pass 6 as the bound (max value) thus nextInt return 0-5
        // then add 1 to correct to 1-6 and return the value
        return rnd.nextInt(6) + 1;
    }

    // main function
    public static void main(String[] args) {

        print("rolling dice...\n");

        // roll dice and get the sum of them
        int r1 = dieRoll();
        int r2 = dieRoll();
        int sum = (r1 + r2);

        // display result
        print("your rolled " + r1 + " + " + r2 + " = " + sum + "\n");

        // employ craps game rules
        if (sum == 2 || sum == 3 || sum == 12) {
            print("craps, better luck next time, you lose\n");
        } else if (sum == 7 || sum == 11) {
            print("congrats, you win\n");
        } else {
            // save users current score and display it
            int point = sum;
            print("your current point is " + point + "\n");

            // use of do while loop rather than other loops
            // as this code MUST execute at least one time
            do {

                // let user know game is still ongoing
                print("rolling again...\n");

                // roll dice and get the sum of them
                r1 = dieRoll();
                r2 = dieRoll();
                sum = (r1 + r2);

                // let user know the result
                print("your rolled " + r1 + " + " + r2 + " = " + sum + "\n");
            } while (sum < point);

            // end game once this point is reached
            if (sum == point) {
                print("congrats, you win\n");
            } else {
                print("craps, better luck next time, you lose\n");
            }
        }
    }
}
