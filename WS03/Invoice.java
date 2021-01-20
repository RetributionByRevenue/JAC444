
public class Invoice implements Payable {

    private String partNumber;
    private String partDesc;
    private int count;
    private double price;

    public Invoice(String partNumber, String partDesc, int count, double price) {
        setPartNumber(partNumber);
        setPartDesc(partDesc);
        setCount(count);
        setPrice(price);
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Invoice{" + " partNumber='" + getPartNumber() + "'" + " partDesc='" + getPartDesc() + "'" + " count='"
                + getCount() + "'" + " price='" + getPrice() + "'" + "}";
    }

    @Override
    public double getPaymentAmount() {
        return (getCount() * getPrice());
    }
}
