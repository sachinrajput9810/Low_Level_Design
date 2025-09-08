public class AbstractFactoryMethod {
    public static void main(String[] args) {
        Factory basicFactory = new BasicFactory();
        String type = "standard";
        Burger basicBurger = basicFactory.createBurger(type);
        basicBurger.prepare();
        
        WheetFactory wheetFactory = new WheetFactory();
        Garlic wheetGarlic = wheetFactory.createGarlic(type);
        wheetGarlic.prepare();
    }
    
}


class BasicBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Burger with basic ingredients like lettuce, tomato, and bun.");
    }
}

class StandardBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Burger with standard ingredients like cheese, lettuce, and bun.");
    }
}

class PremiumBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Burger with premium ingredients like bacon, avocado, and special sauce.");
    }
}

class BasicWheetBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Wheet Burger with basic ingredients like lettuce, tomato, and whole wheat bun.");
    }
}

class StandardWheetBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Wheet Burger with standard ingredients like cheese, lettuce, and whole wheat bun.");
    }    
}

class PremiumWheetBurger implements Burger {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Wheet Burger with premium ingredients like bacon, avocado, and special sauce on whole wheat bun.");
    }    
}

interface Garlic{
    void prepare();
}

class BasicGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Basic Garlic Burger with garlic sauce and basic ingredients.");
    }
}

class StandardGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Garlic Burger with garlic sauce and standard ingredients.");
    }
}

class PremiumGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Garlic Burger with garlic sauce and premium ingredients.");
    }
}

class BasicWheetGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Plain Garlic Burger with garlic sauce.");
    }
}

class StandardWheetGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Standard Wheet Garlic Burger with garlic sauce and standard ingredients.");
    }
}

class PremiumWheetGarlicBread implements Garlic {
    @Override
    public void prepare() {
        System.out.println("Preparing Premium Wheet Garlic Burger with garlic sauce and premium ingredients.");
    }
}

interface Factory {
    Burger createBurger(String type);
    Garlic createGarlic(String type);
}

class BasicFactory implements Factory {
    @Override
    public Burger createBurger(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicBurger();
            case "standard":
                return new StandardBurger();
            case "premium":
                return new PremiumBurger();
            default:
                throw new IllegalArgumentException("Unknown burger type: " + type);
        }
    }

    @Override
    public Garlic createGarlic(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicGarlicBread();
            case "standard":
                return new StandardGarlicBread();
            case "premium":
                return new PremiumGarlicBread();
            default:
                throw new IllegalArgumentException("Unknown garlic type: " + type);
        }
    }
}

class WheetFactory implements Factory {
    @Override
    public Burger createBurger(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicWheetBurger();
            case "standard":
                return new StandardWheetBurger();
            case "premium":
                return new PremiumWheetBurger();
            default:
                throw new IllegalArgumentException("Unknown burger type: " + type);
        }
    }

    @Override
    public Garlic createGarlic(String type) {
        switch (type.toLowerCase()) {
            case "basic":
                return new BasicWheetGarlicBread();
            case "standard":
                return new StandardWheetGarlicBread();
            case "premium":
                return new PremiumWheetGarlicBread();
            default:
                throw new IllegalArgumentException("Unknown garlic type: " + type);
        }
    }
}