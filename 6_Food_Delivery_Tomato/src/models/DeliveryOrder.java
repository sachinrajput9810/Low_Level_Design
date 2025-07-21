package models;

public class DeliveryOrder extends Order{
    private String userAddress;

    public DeliveryOrder(){
        userAddress = "";
    }

    public void setDeliveryAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getDeliveryAddress() {
        return userAddress;
    }


    @Override
    public String getType() {
        return "Delivery";
    }


}
