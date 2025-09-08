package strategies;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card having card number " + cardNumber);
    }
}
