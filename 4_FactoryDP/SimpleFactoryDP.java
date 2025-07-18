
 interface Burger {
        void prepare();
    }

    class CheeseBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Cheese Burger with cheese and bun.");
        }
    }

    class VeggieBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Veggie Burger with vegetables and bun.");
        }
    }

    class ChickenBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Chicken Burger with chicken patty and bun.");
        }
    }

    class BurgerFactory{
        public Burger createBurger(String type) {
            switch (type.toLowerCase()) {
                case "cheese":
                    return new CheeseBurger();
                case "veggie":
                    return new VeggieBurger();
                case "chicken":
                    return new ChickenBurger();
                default:
                    throw new IllegalArgumentException("Unknown burger type: " + type);
            }
        }
    }
public class SimpleFactoryDP {

    public static void main(String[] args) {
        BurgerFactory factory = new BurgerFactory() ;
        Burger cheeseBurger = factory.createBurger("cheese") ;
        cheeseBurger.prepare();
        Burger veggieBurger = factory.createBurger("veggie") ;
        veggieBurger.prepare();
        Burger chickenBurger = factory.createBurger("chicken") ;
        chickenBurger.prepare();
    }

}