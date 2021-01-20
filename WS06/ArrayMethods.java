
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class ArrayMethods {

    public static final ArrayProcessor min = (arr) -> {
        Arrays.sort(arr);
        return arr[0];
    };

    public static final ArrayProcessor max = (arr) -> {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    };

    public static final ArrayProcessor sum = (arr) -> {
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return sum;
    };

    public static final ArrayProcessor avg = (arr) -> {
        return (sum.func(arr) / arr.length);
    };

    public static ArrayProcessor counter(double value) {
        ArrayProcessor cnt = (arr) -> {
            int count = 0;
            for (double num : arr) {
                if (num == value) {
                    count++;
                }
            }
            return count;
        };
        return cnt;
    }

    private static double readDouble() {
        Scanner sc = new Scanner(System.in);
        double entered = 1;
        boolean good = false;
        while (!good) {
            try {
                entered = sc.nextDouble();
                good = true;
            } catch (Exception ignored) {
                sc.next();
                System.out.print("try again: ");
                good = false;
            }
        }
        return entered;
    }

    public static void main(String[] args) {

        System.out.println("enter numbers to add to array");
        System.out.println("enter 0 when done");

        Vector<Double> values = new Vector<Double>();
        double entered = 1;

        while (entered != 0) {
            System.out.print("double to add: ");
            entered = readDouble();
            if (entered != 0) {
                values.add(entered);
            }
        }

        double[] arr = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            arr[i] = values.get(i);
        }

        System.out.println();
        System.out.println("your array: " + values);

        System.out.println();
        System.out.println("array details: ");
        System.out.println("max: " + max.func(arr));
        System.out.println("min: " + min.func(arr));
        System.out.println("sum: " + sum.func(arr));
        System.out.println("avg: " + avg.func(arr));
        System.out.println();

        System.out.print("enter value to count array for: ");
        double searchValue = readDouble();
        ArrayProcessor findFunction = counter(searchValue);
        System.out.println(searchValue + " occured " + findFunction.func(arr) + " times");
        System.out.println();
    }
}
