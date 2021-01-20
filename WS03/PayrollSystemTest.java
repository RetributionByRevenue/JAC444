
public class PayrollSystemTest {
    public static void main(String[] args) {

        // creation of 4 different employee subclasses
        BasePlusCommissionEmployee emp1 = new BasePlusCommissionEmployee("John", "Smith", "123-456-789", 500.00, 0.20,
                25000.00);
        CommissionEmployee emp2 = new CommissionEmployee("Jane", "Doe", "222-444-666", 800.00, 0.12);
        HourlyEmployee emp3 = new HourlyEmployee("Bruce", "Wayne", "999-999-999", 100.00, 0.30);
        SalariedEmployee emp4 = new SalariedEmployee("Clark", "Kent", "333-777-000", 200.00);

        // print employee details
        System.out.println();
        System.out.printf("%s%s: $%,.2f\n", emp1, " earned", emp1.getPaymentAmount());
        System.out.printf("%s%s: $%,.2f\n", emp2, " earned", emp2.getPaymentAmount());
        System.out.printf("%s%s: $%,.2f\n", emp3, " earned", emp3.getPaymentAmount());
        System.out.printf("%s%s: $%,.2f\n", emp4, " earned", emp4.getPaymentAmount());

        Employee[] empArr = new Employee[4];
        empArr[0] = emp1;
        empArr[1] = emp2;
        empArr[2] = emp3;
        empArr[3] = emp4;

        System.out.println();
        System.out.println("Printing Employee data again using polymorphism");

        for (Employee emp : empArr) {
            System.out.printf("%s%s: $%,.2f\n", emp, " earned", emp.getPaymentAmount());
        }

        System.out.println();
        System.out.println("instanceOf test: ");

        for (Employee emp : empArr) {
            if (emp instanceof HourlyEmployee) {
                System.out.println((HourlyEmployee) emp); // cast
                System.out.println(emp.getFirstName() + " is an HourlyEmployee");
            }
        }

        System.out.println();
        System.out.println("getClass test: ");

        for (Employee emp : empArr) {
            System.out
                    .println("Employee: " + emp.getFirstName() + " " + emp.getLastName() + " is of " + emp.getClass());
        }
    }
}
