package rmaharaj14.IncomeTax;

import java.util.Scanner;

public class IncomeTax {

    // CONSTANTS
    private final int SINGLE_FILER = 1;
    private final int MARRIED_JOINTLY = 2;
    private final int MARRIED_SEPARATELY = 3;
    private final int HEAD_OF_HOUSEHOLD = 4;

    // TAX DATA
    private final double[][] TAX_2001 = {{15, 27050, 45200, 22600, 36250}, {27.5, 65550, 109250, 54625, 93650},
            {30.5, 136750, 166500, 83250, 151650}, {35.5, 297350, 297350, 148675, 297350},
            {39.1, 297351, 297351, 148676, 297351}};
    private final double[][] TAX_2009 = {{10, 8350, 16700, 8350, 11950}, {15, 33950, 67900, 33950, 45500},
            {25, 82250, 137050, 68525, 117450}, {28, 171550, 208850, 104425, 190200},
            {33, 372950, 372950, 186475, 372950}, {35, 372951, 372951, 186476, 372951}};

    private double income;
    private int status;

    public IncomeTax(double income, int status) {
        this.income = income;
        this.status = status;
    }

    public IncomeTax() {
        this.income = 0;
        this.status = 0;
    }

    private double getIncome() {
        return income;
    }

    private void setIncome(double income) {
        this.income = income;
    }

    private int getStatus() {
        return status;
    }

    private void setStatus(int status) {
        this.status = status;
    }

    private int readInt() {
        Scanner sc = new Scanner(System.in);
        boolean good = false;
        int val = 0;
        while (!good) {
            try {
                val = sc.nextInt();
                good = true;
            } catch (Exception ignored) {
                sc.next();
                print("try again: ");
                good = false;
            }
        }
        return val;
    }

    private void print(String str) {
        System.out.print(str);
    }

    private double getIncomeTax(int year) {
        double[][] taxYear = (year == 2001 ? TAX_2001 : TAX_2009);
        double tax = 0;
        int row = 0;
        while (getIncome() > taxYear[row][getStatus()]) {
            tax += ((taxYear[row][getStatus()] * taxYear[row][0]) / 100);
            setIncome(getIncome() - taxYear[row][getStatus()]);
            row++;
        }
        if (getIncome() > 0) {
            tax += ((getIncome() * taxYear[row][0]) / 100);
        }
        return tax;
    }

    private void taxMenu() {
        int opt = 10;
        print("\nIncome Tax Calculator:\n");
        while (opt != 0) {
            print("\nSelect your Filing Status:\n");
            print("1 - Single Filer\n");
            print("2 - Married Jointly or Qualifying Widow(er)\n");
            print("3 - Married Filing Separately\n");
            print("4 - Head of Household\n");
            print("0 - Exit\n");
            print("Enter your filing status: ");
            setStatus(readInt());
            if (getStatus() == 0) {
                break;
            }
            print("Enter your taxable income: ");
            setIncome(readInt());
            print("Enter what year tax data to use (2001 or 2009): ");
            int year = readInt();
            double tax = getIncomeTax(year);
            System.out.printf("\nTaxes to pay: $%.2f\n\n", tax);
            print("Enter 0 to exit or 1 to enter new values: ");
            opt = readInt();
        }
    }

    private void printTaxTable(double from, double to, int year) {
        System.out.printf("\n%d tax table for income range $%.2f to $%.2f\n\n", year, from, to);
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "Taxable", "Single", "Married", "Married", "Head of");
        System.out.printf("%-15s%-15s%-15s%-15s%-15s\n\n", "Income", "Filer", "Jointly", "Separately", "Household");
        while (from != (to + 1000)) {
            System.out.printf("%-15.2f", from);
            for (int i = 1; i < 5; i++) {
                setIncome(from);
                setStatus(i);
                System.out.printf("%-15.2f", getIncomeTax(year));
            }
            print("\n");
            from += 1000;
        }
    }

    private void taxTableMenu() {
        print("\nTax Table:\n");
        print("Enter dollar amount from: ");
        double from = readInt();
        print("Enter amount to: ");
        double to = readInt();
        int diff = (int) ((to - from) / 1000);
        boolean err = false;
        if (diff <= 0) {
            print("That range cannot be calculated!\n");
            err = true;
        } else if (from < 10000 || to < 10000) {
            print("those values are too small to chart!\n");
            err = true;
        } else if (diff > 20) {
            if (diff > 40) {
                print("the requested table is too large!");
                err = true;
            } else {
                print("You're requesting a large table of " + diff + " rows\n");
                print("Are you sure you want to continue?\n");
                print("0 - Exit, 1 - Continue: ");
                err = (readInt() == 0);
            }
        }
        if (!err) {
            printTaxTable(from, to, 2001);
            printTaxTable(from, to, 2009);
            print("\nEnter 0 to continue when ready: ");
            readInt();
        }
    }

    public void mainMenu() {
        while (true) {
            print("\nMain Menu:\n");
            print("1 - Compute Your Taxes\n");
            print("2 - Print Tax Table Within Range\n");
            print("0 - Exit\n");
            print("Selection: ");
            int opt = readInt();
            if (opt == 0) {
                print("Exiting...\n");
                break;
            } else {
                switch (opt) {
                    // jdk 14 needed for arrow case
                    case 1 -> taxMenu();
                    case 2 -> taxTableMenu();
                    default -> print("not a valid option!\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        IncomeTax ic = new IncomeTax();
        ic.mainMenu();
    }
}
