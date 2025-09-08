import factory.NowOrderFactory;
import factory.OrderFactory;
import factory.ScheduledOrderFactory;
import managers.OrderManager;
import managers.RestaurantManager;
import models.*;
import services.NotificationService;
import strategies.PaymentStrategy;

import java.util.List;

public class TomatoApp {
    public TomatoApp() {
        initializeRestaurants();
    }

    public void initializeRestaurants() {
        // Initialize the list of restaurants
        Restaurant restaurant1 = new Restaurant("Picasa", "Brazil");
        restaurant1.addMenuItem(new MenuItem("P1" , "Burger", 10.0));
        restaurant1.addMenuItem(new MenuItem("P2" , "Pizza", 20.0));

        Restaurant restaurant2 = new Restaurant("KFC", "USA");
        restaurant2.addMenuItem(new MenuItem("P1" , "Cheese Burger", 10.0));
        restaurant2.addMenuItem(new MenuItem("P2" , "Chicken Burger", 20.0));

        Restaurant restaurant3 = new Restaurant("Burger King", "USA");
        restaurant3.addMenuItem(new MenuItem("P1" , "Chowmein", 10.0));
        restaurant3.addMenuItem(new MenuItem("P2" , "Samosa", 20.0));

        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);


        // Adding more restaurants
    }

    public List<Restaurant> searchRestaurantsByLocation(String location) {
        return RestaurantManager.getInstance().getRestaurantsByLocation(location);
    }

    public void selectRestaurant(User user , Restaurant restaurant){
        Cart cart = user.getCart() ;
        cart.setRestaurant(restaurant) ;
    }

    public void addToCart(User user , String menuItemCode){
        Restaurant restaurant = user.getCart().getRestaurant() ;
        if(restaurant == null) {
            System.out.println("Please select a restaurant first.");
            return;
        }
        for(MenuItem menuItem : restaurant.getMenuItems()) {
            if(menuItem.getCode().equals(menuItemCode)) {
                user.getCart().addItem(menuItem);
                return;
            }
        }
    }

    public Order checkOutNow(User user , String orderType , PaymentStrategy paymentStrategy){
        return checkout(user , orderType , paymentStrategy , new NowOrderFactory());
    }

    public Order scheduleOrder(User user , String scheduledTime , String orderType , PaymentStrategy paymentStrategy){
        return checkout(user , orderType , paymentStrategy , new ScheduledOrderFactory(scheduledTime));
    }

    public Order checkout(User user , String orderType , PaymentStrategy paymentStrategy , OrderFactory orderFactory){
        if(user.getCart().isEmpty() || user.getCart().getRestaurant() == null) {
            System.out.println("Cart is empty or no restaurant is selected. Please select a restaurant and add items to the cart.");
            return null;
        }
        Cart cart = user.getCart() ;
        Restaurant orderRestaurant = cart.getRestaurant() ;
        List<MenuItem> menuItems = cart.getMenuItems() ;
        double totalAmount = cart.getTotalAmount() ;

        Order order = orderFactory.createOrder(user , cart , orderRestaurant , menuItems , paymentStrategy , totalAmount , orderType) ;
        OrderManager.getInstance().addOrder(order) ;
        return order ;
    }

    public void payForOrder(User user , Order order){
        boolean isPaymentSuccessful = order.processPayment() ;
        if(isPaymentSuccessful){
            NotificationService notificationService = new NotificationService() ;
            notificationService.sendNotification(order) ;
            user.getCart().clearCart() ;
        }
    }

    public void printCart(User user){
        Cart cart = user.getCart() ;
        System.out.println("Cart Items : ");
        for(MenuItem menuItem : cart.getMenuItems()) {
            System.out.println(menuItem.getCode() + " :: " + menuItem.getName() + " - Rs " + menuItem.getPrice());
        }
        System.out.println("Total Amount : " + cart.getTotalAmount());
    }





}











