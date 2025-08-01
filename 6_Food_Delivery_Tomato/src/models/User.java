package models;

public class User {
    private int id;
    private String name;
    private String address;
    private Cart cart;

    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        cart = new Cart();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

}
