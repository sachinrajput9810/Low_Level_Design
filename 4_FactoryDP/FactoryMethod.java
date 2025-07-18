

    interface Burger{
        void prepare();
    }
    class PlainCheeseBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing PLAIN Cheese Burger with cheese and bun.");
        }
    }
    class PlainVeggieBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing PLAIN Veggie Burger with vegetables and bun.");
        }
    }
    class PlainChickenBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing PLAIN Chicken Burger with chicken patty and bun.");
        }
    }

    class SinghCheeseBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Singh Cheese Burger with extra cheese and special bun.");
        }
    }
    class SinghVeggieBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Singh Veggie Burger with extra vegetables and special bun.");
        }
    }
    class SinghChickenBurger implements Burger {
        @Override
        public void prepare() {
            System.out.println("Preparing Singh Chicken Burger with extra chicken and special bun.");
        }
    }

    interface BurgerFactory {
        public Burger createBurger(String type);
    }

    class PlainBurgerFactory implements BurgerFactory {
        @Override
        public Burger createBurger(String type) {
            switch (type.toLowerCase()) {
                case "cheese":
                    return new PlainCheeseBurger();
                case "veggie":
                    return new PlainVeggieBurger();
                case "chicken":
                    return new PlainChickenBurger();
                default:
                    throw new IllegalArgumentException("Unknown burger type: " + type);
            }
        }
    }

    class SinghBurgerFactory implements BurgerFactory {
        @Override
        public Burger createBurger(String type){
            switch (type.toLowerCase()) {
                case "cheese":
                    return new SinghCheeseBurger();
                case "veggie":
                    return new SinghVeggieBurger();
                case "chicken":
                    return new SinghChickenBurger();
                default:
                    throw new IllegalArgumentException("Unknown burger type: " + type);
            }
        }
    }

public class FactoryMethod {
    public static void main(String[] args) {
        BurgerFactory plainFactory = new PlainBurgerFactory();
        BurgerFactory singhFactory = new SinghBurgerFactory();
        
        Burger cheesBurger = plainFactory.createBurger("cheese");
        cheesBurger.prepare();
        Burger veggieBurger = singhFactory.createBurger("veggie");
        veggieBurger.prepare();
        Burger chickenBurger = plainFactory.createBurger("chicken");
        chickenBurger.prepare();
    }

}
