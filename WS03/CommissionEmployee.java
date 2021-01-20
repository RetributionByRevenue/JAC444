
public class CommissionEmployee extends Employee {
    private double grossSales;
    private double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String SSN, double grossSales, double commissionRate) {
        super(firstName, lastName, SSN);
        try {
            setGrossSales(grossSales);
            setCommissionRate(commissionRate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double getGrossSales() throws Exception {
        if (grossSales <= 0.0) {
            throw new Exception("ERR: grossSales <= 0");
        } else {
            return grossSales;
        }
    }

    public void setGrossSales(double grossSales) throws Exception {
        if (grossSales <= 0.0) {
            throw new Exception("ERR: grossSales <= 0");
        } else {
            this.grossSales = grossSales;
        }
    }

    public double getCommissionRate() throws Exception {
        if (commissionRate < 0.0 || commissionRate > 1.0) {
            throw new Exception("ERR: commissionRate not between 0.0 and 1.0");
        } else {
            return commissionRate;
        }
    }

    public void setCommissionRate(double commissionRate) throws Exception {
        if (commissionRate < 0.0 || commissionRate > 1.0) {
            throw new Exception("ERR: commissionRate not between 0.0 and 1.0");
        } else {
            this.commissionRate = commissionRate;
        }
    }

    @Override
    public double getPaymentAmount() {
        try {
            return (getCommissionRate() * getGrossSales());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return "CommissionEmployee{" + " grossSales='" + getGrossSales() + "'" + " commissionRate='"
                    + getCommissionRate() + "'" + " " + super.toString() + "}";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
