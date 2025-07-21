package services;

import models.MenuItem;
import models.Order;

public class NotificationService {
    public void sendNotification(Order order) {
        System.out.println("\nNotification : New " + order.getType() + " Order placed for " +
                order.getUser().getName() + " of total amount " + order.getTotalAmount() +
                " scheduled for " + order.getScheduled());
        System.out.println("Item list includes");
        for (MenuItem item : order.getMenuItems()) {
            System.out.println(item.getName() + " - Rs " + item.getPrice());
        }
        System.out.println("Thank you for using our services.");
    }
}
