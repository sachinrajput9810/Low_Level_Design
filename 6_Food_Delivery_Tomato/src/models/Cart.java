package models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Restaurant restaurant;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Cart() {
        this.restaurant = null ;
    }

    public double getTotalAmount() {
        double total = 0;
        for (MenuItem item : menuItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void addItem(MenuItem item) {
        if(restaurant == null){
            System.err.println("Set the restaurant before adding items to cart.");
        }
        menuItems.add(item);
    }

    public void clearCart() {
        restaurant = null;
        menuItems.clear();
    }

    public boolean isEmpty() {
        return restaurant == null || menuItems.isEmpty();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }


}
