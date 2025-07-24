public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder();
    }
}



class InventoryService{
    public void checkInventory(){
        System.out.println("Inventory Checked");
    }
}

class PaymentService{
    public void processPayment(){
        System.out.println("Payment done");
    }
}

class ShippingService{
    public void shipOrder(){
        System.out.println("Order Shipped");
    }
}

class OrderService{
    public void placeOrder(){
        System.out.println("Order Placed");
    }
}

class OrderFacade{
    private InventoryService inventoryService ;
    private PaymentService paymentService ;
    private ShippingService shippingService ;
    private OrderService orderService ;
    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.orderService = new OrderService();
    }
    public void placeOrder(){
        inventoryService.checkInventory();
        paymentService.processPayment();
        shippingService.shipOrder();
        orderService.placeOrder();
    }
}






