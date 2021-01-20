
public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String SSN, double grossSales,
                                      double commissionRate, double baseSalary) {
        super(firstName, lastName, SSN, grossSales, commissionRate);
        try {
            setBaseSalary(baseSalary);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double getBaseSalary() throws Exception {
        if (baseSalary <= 0.0) {
            throw new Exception(" ERR: baseSalary <= 0.0");
        } else {
            return (baseSalary * 0.10); // add 10% to base salary
        }
    }

    public void setBaseSalary(double baseSalary) throws Exception {
        if (baseSalary <= 0.0) {
            throw new Exception(" ERR: baseSalary <= 0.0");
        } else {
            this.baseSalary = baseSalary;
        }
    }

    @Override
    public String toString() {
        try {
            return "BasePlusCommissionEmployee{" + " baseSalary='" + getBaseSalary() + "'" + " " + super.toString()
                    + "}";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
