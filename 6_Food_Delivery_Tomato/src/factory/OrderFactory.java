package factory;

import models.*;
import strategies.PaymentStrategy;

import java.util.List;

public interface OrderFactory {
   public Order  createOrder(User user , Cart cart , Restaurant restaurant , List<MenuItem> menuItems ,
                             PaymentStrategy paymentStrategy , double totalAmount , String orderType);
}
