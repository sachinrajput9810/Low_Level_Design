import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface DiscountStrategy{
    double calculate(double baseAmount) ;
}

// Discount strategy ( strategy pattern )

class FlatDiscountStrategy implements DiscountStrategy{
    private final double amount ;

    public FlatDiscountStrategy(double amount){
        this.amount =  amount ;
    }
    @Override
    public double calculate(double baseAmount){
        return Math.min(amount , baseAmount) ;
    }
}

class PercentageDiscountStrategy implements DiscountStrategy{
    private final double percent ;
    public PercentageDiscountStrategy(double percent){
        this.percent = percent ;
    }

    @Override
    public double calculate(double amount){
        return (amount*percent)/100.0 ;
    }
}

class PercentageWithCapStrategy implements  DiscountStrategy{
    private final double percent ;
    private final double cap ;

    PercentageWithCapStrategy(double percent, double cap) {
        this.percent = percent;
        this.cap = cap;
    }

    @Override
    public double calculate(double amount){
        double discount = (percent*amount)/100.0 ;
        return Math.min(discount , cap) ;
    }
}

// strategy type

enum DiscountStrategyType{
    FLAT ,
    PERCENTAGE ,
    PERCENTAGE_WITH_CAP
}

// Discount strategy manager  , singleton  , getDiscountStrategy()

class DiscountStrategyManager{
    private static DiscountStrategyManager INSTANCE ;
    private DiscountStrategyManager() {} ;

    public static synchronized DiscountStrategyManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DiscountStrategyManager() ;
        }
        return INSTANCE ;
    }

    public DiscountStrategy getDiscountStrategy(DiscountStrategyType type , double param1 ){
        return getDiscountStrategy( type ,  param1  , 0.0) ;
    }

    public DiscountStrategy getDiscountStrategy(DiscountStrategyType type , double param1 , double param2){
        return switch(type){
            case FLAT -> new FlatDiscountStrategy(param1) ;
            case PERCENTAGE -> new PercentageDiscountStrategy(param1) ;
            case PERCENTAGE_WITH_CAP -> new PercentageWithCapStrategy(param1 , param2) ;
            default -> throw new IllegalArgumentException("Invalid discount strategy type") ;
        } ;
    }
}

// Product
class Product{
    private final String name ;
    private final String category ;
    private final double price ;
    public Product(String name , String category , double price){
        this.name = name ;
        this.category = category ;
        this.price = price ;
    }

    public double getPrice(){
        return price ;
    }

    public String getCategory(){
        return category ;
    }

    public String getName(){
        return name ;
    }
}

// Cart Item

class CartItem{
    private final Product product ;
    private final int quantity ;
    public CartItem(Product product , int quantity){
        this.product = product ;
        this.quantity = quantity ;
    }
    public double calculateTotal(){
        return product.getPrice() * quantity ;
    }

    public Product getProduct(){
        return product ;
    }

    public double itemTotal(){
        return product.getPrice() * quantity ;
    }
}

// Cart
class Cart{
    private final List<CartItem> cartItemsList = new ArrayList<>() ;
    private double originalTotal = 0.0 ;
    private double currentTotal = 0.0 ;
    private boolean loyaltyMember  ;
    private String paymentBank ;

    public Cart(){
        loyaltyMember = false ;
        paymentBank = "" ;
    }

    public double getOriginalTotal(){
        return originalTotal ;
    }

    public double getCurrentTotal(){
        return currentTotal ;
    }

    public void applyDiscount(double amount){
        currentTotal -= amount ;
        if(currentTotal < 0){
            currentTotal = 0.0 ;
        }
    }

    public void addProduct(Product product , int quantity){
        cartItemsList.add(new CartItem(product , quantity)) ;
        currentTotal += product.getPrice() * quantity ;
        originalTotal += product.getPrice() * quantity ;
    }

    public void setLoyaltyMember(boolean loyaltyMember){
        this.loyaltyMember = loyaltyMember ;
    }

    public void setPaymentBank(String paymentBank){
        this.paymentBank = paymentBank ;
    }

    public boolean isLoyaltyMember(){
        return loyaltyMember ;
    }

    public String getPaymentBank(){
        return paymentBank ;
    }

    public List<CartItem> getCartItemsList(){
        return cartItemsList ;
    }
}

// Discount Coupon
abstract class Coupon{
    private Coupon next ;
    public void setNext(Coupon next){
        this.next = next ;
    }
    public Coupon getNext(){
        return next ;
    }

    public void applyDiscount(Cart cart){
        if(isApplicable(cart)){
            double discount = getDiscount(cart) ;
            cart.applyDiscount(discount) ;
            System.out.println(name() + "applied :: " + discount);
            if(!isCombinable()) return;
        }
        if(next != null) next.applyDiscount(cart);
    }

    public abstract boolean isApplicable(Cart cart) ;
    public abstract double getDiscount(Cart cart) ;
    public boolean isCombinable(){
        return true ;
    }
    public abstract String name() ;
}


// Discount coupon types
class SeasonalOffer extends Coupon{
    private double percent ;
    private String category ;
    private DiscountStrategy strategy ;
    public SeasonalOffer(double percent , String category){
        this.percent = percent ;
        this.category = category ;
        strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(DiscountStrategyType.PERCENTAGE , percent);
    }


    @Override
    public boolean isApplicable(Cart cart) {
        for(CartItem item : cart.getCartItemsList()){
            if(item.getProduct().getCategory().equals(category)){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public double getDiscount(Cart cart) {
        double subDiscount = 0.0 ;
        for(CartItem item : cart.getCartItemsList()){
            if(item.getProduct().getCategory().equals(category)) subDiscount += item.itemTotal() ;
        }
        return strategy.calculate(subDiscount) ;
    }

    @Override
    public String name() {
        return "Seasonal offer " + (int)percent + "% off on " + category ;
    }
}

class LoyaltyDiscount extends Coupon{
    private double percent ;
    private DiscountStrategy strategy ;
    public LoyaltyDiscount(double percent){
        this.percent = percent ;
        strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(DiscountStrategyType.PERCENTAGE , percent);
    }
    @Override
    public boolean isApplicable(Cart cart){
        return cart.isLoyaltyMember() ;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculate(cart.getCurrentTotal());
    }

    @Override
    public String name() {
        return "Loyalty discount " + (int)percent + " % off" ;
    }
}

class BulkPurchaseDiscount extends Coupon{
    private double threshold ;
    private double flatOff ;
    private DiscountStrategy strategy ;
    public BulkPurchaseDiscount(double threshold , double flatOff){
        this.flatOff = flatOff ;
        this.threshold = threshold ;
        strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(DiscountStrategyType.FLAT , flatOff);
    }


    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getOriginalTotal() >= threshold ;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculate(cart.getCurrentTotal()) ;
    }

    @Override
    public String name() {
        return "Bulk purchase Rs " + (int)flatOff + " over " + (int)threshold ;
    }
}

class BankingCoupon extends Coupon{
    private String bankName ;
    private double minSpend ;
    private double percent ;
    private double offCap ;
    private DiscountStrategy strategy ;

    public BankingCoupon(String bankName , double minSpend , double percent , double offCap ){
        this.bankName = bankName ;
        this.minSpend = minSpend ;
        this.percent = percent ;
        this.offCap = offCap ;
        strategy = DiscountStrategyManager.getInstance().getDiscountStrategy(DiscountStrategyType.PERCENTAGE_WITH_CAP , percent , offCap);
    }


    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getPaymentBank().equals(bankName) && cart.getOriginalTotal() >= minSpend ;
    }

    @Override
    public double getDiscount(Cart cart) {
        return strategy.calculate(cart.getCurrentTotal()) ;
    }

    @Override
    public String name() {
        return bankName + " bank Rs :: "  + (int)percent + " % off with cap " + (int)offCap ;
    }
}

class CouponManager {
    private static CouponManager INSTANCE ;
    private Coupon head ;
    private final Lock lock = new ReentrantLock() ;

    private CouponManager(){
    }
    public static CouponManager getInstance(){
        if(INSTANCE == null) INSTANCE = new CouponManager() ;
        return INSTANCE ;
    }

    public void addCoupon(Coupon coupon){
        lock.lock(); ;
        try {
            if(head == null) head = coupon ;
            else{
                Coupon curr = head ;
                while(curr.getNext() != null) curr = curr.getNext() ;
                curr.setNext(coupon) ;
            }
        }
        finally {
            lock.unlock();
        }
    }

    public List<String> getApplicableCoupons(Cart cart){
        lock.lock(); ;
        try{
            List<String> couponList = new ArrayList<>() ;
            Coupon curr = head ;
            while(curr != null){
                couponList.add(curr.name()) ;
                curr = curr.getNext() ;
            }
            return couponList ;
        }
        finally {
            lock.unlock();
        }
    }

    public double applyAll(Cart cart){
        lock.lock();
        try{
            if(head != null){
                head.applyDiscount(cart);
            }
            return cart.getCurrentTotal() ;
        }
        finally {
            lock.unlock();
        }
    }
}










public class Main {
    public static void main(String[] args) {
        CouponManager manager = CouponManager.getInstance() ;
        manager.addCoupon(new SeasonalOffer(10 , "Clothing"));
        manager.addCoupon(new LoyaltyDiscount(5));
        manager.addCoupon(new BulkPurchaseDiscount(1000 , 100));
        manager.addCoupon(new BankingCoupon("Axis" , 2000 , 15 , 500));

        Product product1 = new Product("Winter Jacket" , "Clothing" , 1000) ;
        Product product2 = new Product("Smart Phone" , "Electronics" , 20000) ;
        Product product3 = new Product("Jeans" , "Clothing" , 1000) ;
        Product product4 = new Product("Headpgones" , "Electronics" , 2000) ;

        Cart cart = new Cart() ;
        cart.addProduct(product1 , 1) ;
        cart.addProduct(product2 , 1) ;
        cart.addProduct(product3 , 2) ;
        cart.addProduct(product4 , 1) ;
        cart.setLoyaltyMember(true);
        cart.setPaymentBank("Axis");

        System.out.println("Total original cart value :: Rs " + cart.getOriginalTotal());

        List<String> couponList = new ArrayList<>() ;
        couponList = manager.getApplicableCoupons(cart) ;
        System.out.println("Applicable coupons : ") ;
        for(String coupon : couponList){
            System.out.println(" - " + coupon) ;
        }

        System.out.println("Total after discounts :: Rs " + manager.applyAll(cart));


    }
}