package factory;

import models.*;
import strategies.PaymentStrategy;

import java.util.List;

public class ScheduledOrderFactory implements OrderFactory{

    private String scheduledTime ;
    public ScheduledOrderFactory(String scheduledTime){
        this.scheduledTime = scheduledTime ;
    }


    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                             PaymentStrategy paymentStrategy, double totalAmount, String orderType)
    {
        Order order = null ;
        if(orderType.equalsIgnoreCase("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setDeliveryAddress(user.getAddress());
            order = deliveryOrder;
        }
        else{
            PickupOrder pickupOrder = new PickupOrder();
            pickupOrder.setRestaurantAddress(restaurant.getAddress());
            order = pickupOrder;
        }
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setMenuItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setScheduled(scheduledTime);
        order.setTotalAmount(totalAmount);
        return order;
    }
}
