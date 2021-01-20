
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixAddition implements Runnable {

    private final double[][] a;
    private final double[][] b;

    MatrixAddition(double[][] a, double[][] b) {
        this.a = a;
        this.b = b;
    }

    public void run() {

        double[][] c = new double[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
    }
}

class Main {
    public static void main(String[] args) throws Exception {

        double[][] a = new double[2000][2000];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = Math.random();
            }
        }

        double[][] b = new double[2000][2000];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = Math.random();
            }
        }

        System.out.println("Beginning sequential test...");
        Thread sequentialThread = new Thread(new MatrixAddition(a, b));
        long start = System.currentTimeMillis();
        sequentialThread.start();
        sequentialThread.join();
        long end = System.currentTimeMillis();
        System.out.printf("Sequential matrix addition took %d milliseconds\n", (end - start));

        System.out.println("\nBeginning parallel test...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        start = System.currentTimeMillis();
        executorService.execute(new MatrixAddition(Arrays.copyOfRange(a, 0, 500), Arrays.copyOfRange(b, 0, 500)));
        executorService.execute(new MatrixAddition(Arrays.copyOfRange(a, 501, 1000), Arrays.copyOfRange(b, 501, 1000)));
        executorService
                .execute(new MatrixAddition(Arrays.copyOfRange(a, 1001, 1500), Arrays.copyOfRange(b, 1001, 1500)));
        executorService
                .execute(new MatrixAddition(Arrays.copyOfRange(a, 1501, 2000), Arrays.copyOfRange(b, 1501, 2000)));
        executorService.shutdown();
        end = System.currentTimeMillis();
        System.out.printf("Parallel matrix addition took %d milliseconds\n", (end - start));
    }
}
