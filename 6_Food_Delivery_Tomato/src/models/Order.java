package models;

import strategies.PaymentStrategy;

import java.util.List;

public abstract class Order {
    private static int idCounter = 0;
    private int orderId;
    private User user ;
    private Restaurant restaurant ;
    private List<MenuItem> menuItems ;
    private PaymentStrategy paymentStrategy ;
    private double totalAmount ;
    private String scheduled ;

    public Order() {
        orderId = ++idCounter;
        this.user = null;
        this.restaurant = null;
        this.paymentStrategy = null;
        this.totalAmount = 0.0;
        this.scheduled = "";
    }

    public boolean processPayment(){
        if(paymentStrategy != null){
            paymentStrategy.pay(totalAmount);
            return true;
        }
        else{
            System.out.println("Payment strategy not set.");
            return false;
        }
    }

    public abstract String getType();

    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
