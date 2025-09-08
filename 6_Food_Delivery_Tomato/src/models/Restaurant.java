package models;

import java.util.ArrayList;
import java.util.List;
public class Restaurant {
    private static int idCounter = 0;
    private int id ;
    private String name;
    private String address;
    List<MenuItem> menuItems;   

    public Restaurant(String name, String address) {
        this.id = ++idCounter;
        this.name = name;
        this.address = address;
        menuItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    

}
