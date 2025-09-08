import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import strategies.UPIPaymentStrategy;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TomatoApp tomatoApp = new TomatoApp();

        User user = new User(1 , "John Doe" , "USA" ) ;
        System.out.println("Hello, " + user.getName() + "! Welcome to TomatoApp!") ;

        // Search for restaurants by location
        String location = "USA" ;
        List<Restaurant> restaurants = tomatoApp.searchRestaurantsByLocation(location) ;
        if(restaurants.isEmpty()) {
            System.out.println("No restaurants found in " + location + ". Please try a different location.");
            return ;
        }
        System.out.print("Restaurants in " + location + " : \n");
        for(Restaurant restaurant : restaurants) {
            System.out.println("- " +   restaurant.getName() );
        }

        tomatoApp.selectRestaurant(user , restaurants.get(0)) ;
        System.out.println("Selected Restaurant :: " + restaurants.get(0).getName()) ;

        tomatoApp.addToCart(user , "P1") ;
        tomatoApp.addToCart(user , "P2") ;

        tomatoApp.printCart(user) ;

        Order order = tomatoApp.checkOutNow(user , "Delivery" , new UPIPaymentStrategy("9819212345"));
        tomatoApp.payForOrder(user , order) ;


    }
}