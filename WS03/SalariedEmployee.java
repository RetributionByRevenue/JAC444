
public class SalariedEmployee extends Employee {
    private double weeklySalary;

    public SalariedEmployee(String firstName, String lastName, String SSN, double weeklySalary) {
        super(firstName, lastName, SSN);
        try {
            setWeeklySalary(weeklySalary);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double getWeeklySalary() throws Exception {
        if (weeklySalary <= 0.0) {
            throw new Exception(" ERR: weeklySalary <= 0.0");
        } else {
            return weeklySalary;
        }
    }

    public void setWeeklySalary(double weeklySalary) throws Exception {
        if (weeklySalary <= 0.0) {
            throw new Exception(" ERR: weeklySalary <= 0.0");
        } else {
            this.weeklySalary = weeklySalary;
        }
    }

    @Override
    public double getPaymentAmount() {
        try {
            return getWeeklySalary();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return "SalariedEmployee{" + " weeklySalary='" + getWeeklySalary() + "'" + " " + super.toString() + "}";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
