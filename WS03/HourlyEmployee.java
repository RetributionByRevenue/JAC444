
public class HourlyEmployee extends Employee {
    private double wage;
    private double hours;

    public HourlyEmployee(String firstName, String lastName, String SSN, double wage, double hours) {
        super(firstName, lastName, SSN);
        try {
            setWage(wage);
            setHours(hours);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public double getWage() throws Exception {
        if (wage <= 0.0) {
            throw new Exception(" ERR: HourlyEmployee wage is <= 0.0");
        } else {
            return wage;
        }
    }

    public void setWage(double wage) throws Exception {
        if (wage <= 0.0) {
            throw new Exception(" ERR: HourlyEmployee wage is <= 0.0");
        } else {
            this.wage = wage;
        }
    }

    public double getHours() throws Exception {
        if (hours < 0.0 || hours > 168.0) {
            throw new Exception(" ERR: HourlyEmployee hours is not between 0.0 and 168.0");
        } else {
            return hours;
        }
    }

    public void setHours(double hours) throws Exception {
        if (hours < 0.0 || hours > 168.0) {
            throw new Exception(" ERR: HourlyEmployee hours is not between 0.0 and 168.0");
        } else {
            this.hours = hours;
        }
    }

    @Override
    public double getPaymentAmount() {
        try {
            return (getHours() * getWage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return "HourlyEmployee{" + " wage='" + getWage() + "'" + " hours='" + getHours() + "'" + " "
                    + super.toString() + "}";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
