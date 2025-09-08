package strategies;

public class UPIPaymentStrategy implements PaymentStrategy {
    private String mobile ;
    public UPIPaymentStrategy(String mobile) {
        this.mobile = mobile;
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + amount + " using UPI on " + mobile);
    }
}
