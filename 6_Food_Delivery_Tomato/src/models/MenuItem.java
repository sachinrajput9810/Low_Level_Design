package models;
public class MenuItem {
    String code ;
    String name;
    double price;
    public MenuItem(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    } 
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCode(String code) {
        this.code = code;
    }


}
