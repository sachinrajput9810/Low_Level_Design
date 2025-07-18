
import java.util.ArrayList;
import java.util.List;

public class OCP{
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Apple", 0.99));
        cart.addProduct(new Product("Banana", 0.59));

        printShoppingList printer = new printShoppingList(cart);
        printer.print();

        // Saving to different databases
        ShoppingCartStorage mongoDB = new MongoDB();
        mongoDB.saveToDB(cart);

        ShoppingCartStorage mySQL = new MySQL();
        mySQL.saveToDB(cart);

        ShoppingCartStorage cassandraDB = new CassandraDB();
        cassandraDB.saveToDB(cart);

    }

    public static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    public static class ShoppingCart{
        public ShoppingCart() {
            System.out.println("ShoppingCart created ::");
        }

        private List<Product> products = new ArrayList<>();

        public void addProduct(Product product) {
            System.out.println("Adding product: " + product.getName() + ", Price: " + product.getPrice());
            products.add(product);  
        }

        public List<Product> getProducts() {
            return new ArrayList<>(products);
        }

        public double calculateTotal(){
            double total = 0;
            for (Product product : products) {
                total += product.getPrice() ;
            }
            return total;
        }
    }


    public static class printShoppingList{
        private ShoppingCart shoppingCart;

        public printShoppingList(ShoppingCart shoppingCart) {
            this.shoppingCart = shoppingCart;
        }

        public void print() {
            System.out.println("Shopping List:");
            for (Product product : shoppingCart.getProducts()) {
                System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice());
            }
            System.out.println("Total: " + shoppingCart.calculateTotal());
        }
    }

    public interface ShoppingCartStorage{
        public void saveToDB(ShoppingCart cart); ;
    }

    public static class MongoDB implements ShoppingCartStorage {
        @Override
        public void saveToDB(ShoppingCart cart) {
            System.out.println("Saving shopping cart items to MongoDB");
        }
    }

    public static class MySQL implements ShoppingCartStorage {
        @Override
        public void saveToDB(ShoppingCart cart) {
            System.out.println("Saving shopping cart items to MySQL");
        }
    }

    public static class CassandraDB implements ShoppingCartStorage {
        @Override
        public void saveToDB(ShoppingCart cart) {
            System.out.println("Saving shopping cart items to CassandraDB");
        }
    }
}

