
import java.util.Date;

public class Time implements Comparable<Time>, Cloneable {
    // Time class implements both Comparable and Cloneable

    private final long seconds;
    // Only class data member, as its the only required

    Time() {
        this.seconds = System.currentTimeMillis();
    }

    Time(long seconds) {
        this.seconds = seconds;
    }

    Time(int hours, int minutes, int seconds) {
        seconds += ((hours * 60 * 60) + (minutes * 60));
        this.seconds = new Date(seconds).getTime();
    }

    // Multiple constructors, although default constructor is never used

    public long getHour() {
        return new Date(seconds).getHours();
    }

    public long getMinute() {
        return new Date(seconds).getMinutes();
    }

    public long getSecond() {
        return new Date(seconds).getSeconds();
    }

    public long getSeconds() {
        return new Date(seconds).getTime();
    }

    // Simple getter functions using the Date class for easy parsing

    @Override
    public int compareTo(Time comparable) {
        return (int) (this.seconds - comparable.seconds);
    }

    // Comparable interface override method
    // returns the current classes seconds subtracted from the provided classes
    // seconds
    // then cast to int as the override method must be of return type int

    @Override
    public String toString() {
        return String.format("%d hours %d minutes %d seconds", getHour(), getMinute(), getSecond());
    }

    // Override of toString as per requirement

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (Time) super.clone();
    }

    // Override of Cloneable interface's clone() method
}

// Main class for testing and running
class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Throws CloneNotSupportedException as this is needed to use the .clone()
        // method
        System.out.println("time1: 331 34 674");
        Time time1 = new Time(331, 34, 674);
        System.out.println(time1);
        System.out.println("seconds elapsed in time1: " + time1.getSeconds());
        System.out.println();
        System.out.println("time2: 93889345");
        Time time2 = new Time(93889345);
        System.out.println(time2);
        System.out.println("seconds elapsed in time1: " + time2.getSeconds());
        System.out.println("time1.compareTo(time2): " + time1.compareTo(time2));
        System.out.println();
        System.out.println("time3 created as clone of time1");
        Time time3 = (Time) time1.clone();
        System.out.println("time1.compareTo(time3): " + time1.compareTo(time3));
    }
}
