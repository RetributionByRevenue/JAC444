
// import java scanner for input

import java.util.Scanner;

// public class needed as java is pure object oriented
public class MaxLocation {

    // private print function for easier coding
    private static void print(String str) {
        System.out.print(str);
    }

    // main function
    public static void main(String[] args) {

        // create scanner object for reading input
        Scanner sc = new Scanner(System.in);

        // display startup message
        print("now creating a 2D array\n");

        // read number of rows
        print("how many rows?: ");
        int rows = sc.nextInt();

        // read number of columns for each row
        print("how many columns?: ");
        int cols = sc.nextInt();

        // create the new 2D array using user provided lengths
        double[][] arr = new double[rows][cols];

        // prompt the user to enter a value for each point of the 2D array
        // iterate over the array and record the values
        print("\nenter the array: \n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                // prompt before each entry for clarification
                print("Enter the value for row " + i + " column " + j + ": \t");
                arr[i][j] = sc.nextDouble();
            }
        }

        // display the entered 2D array to user
        print("\nhere is the 2D array you entered: \n");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                print(" " + arr[i][j] + " ");
            }
            print("\n");
        }

        print("\nfinding the highest value... \n");
        // iterate over 2D array looking for the highest entered value
        double maxVal = 0;
        int maxRow = 0, maxCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                // save current value if larger than the previous value
                if (arr[i][j] > maxVal) {
                    maxVal = arr[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        // report the highest found value to user
        // with the location where it was found
        print("the max value I found was " + maxVal + " which I found at row " + maxRow + " and column " + maxCol
                + "\n");

        // close the scanner object cleanly
        sc.close();
    }
}
