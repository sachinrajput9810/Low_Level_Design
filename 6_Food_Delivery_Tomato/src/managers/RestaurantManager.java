package managers;

import java.util.List;
import java.util.ArrayList;
import models.Restaurant;

public class RestaurantManager {
    List<Restaurant> restaurants;
    public RestaurantManager() {
        restaurants = new ArrayList<>();
    }

    private static RestaurantManager instance;
    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<Restaurant> getRestaurantsByLocation(String address){
        List<Restaurant> restaurantsByLocation = new ArrayList<>();
        for(Restaurant restaurant : restaurants){
            if(restaurant.getAddress().equalsIgnoreCase(address)){
                restaurantsByLocation.add(restaurant);
            }
        }
        return restaurantsByLocation;
    }

}
