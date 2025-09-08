package managers;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private OrderManager() {}
    private static OrderManager instance;
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void ListOrders() {
        System.out.println("\n All Orders: ");
        for (Order order : orders) {
            System.out.println(order.getType() + " Order for " + order.getUser().getName() + " of total amount " +
                    order.getTotalAmount() + " scheduled for " + order.getScheduled());
        }
    }

}
