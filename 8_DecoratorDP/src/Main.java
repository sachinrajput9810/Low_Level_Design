public class Main {
    public static void main(String[] args) {
        Pizza pizza = new plainPiza();
        pizza = new CheeseDecorator(pizza);
        pizza = new OnionDecorator(pizza);
        System.out.println(pizza.description());
        System.out.println(pizza.cost());
    }
}

interface Pizza{
    public String description() ;
    public int cost() ;
}
class plainPiza implements Pizza{
    public String description() {
        return "Plain Pizza";
    }
    public int cost() {
        return 100;
    }
}
abstract class pizzaDecorator implements Pizza{
    protected Pizza pizza ;

    public pizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseDecorator extends  pizzaDecorator{
    public CheeseDecorator(Pizza pizza){
        super(pizza);
    }
    public String description(){
        return pizza.description() + " with cheese" ;
    }

    public int cost(){
        return pizza.cost() + 50;
    }
}

class MushroomDecorator extends  pizzaDecorator{
    public MushroomDecorator(Pizza pizza){
        super(pizza) ;
    }
    public String description(){
        return pizza.description() + " with mushroom" ;
    }
    public int cost(){
        return pizza.cost() + 20;
    }
}

class OnionDecorator extends pizzaDecorator{
    public OnionDecorator(Pizza pizza){
        super(pizza) ;
    }

    public String description(){
        return pizza.description() + " with onion" ;
    }
    public int cost(){
        return pizza.cost() + 10;
    }
}