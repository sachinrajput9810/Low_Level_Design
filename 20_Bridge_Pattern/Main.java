interface Engine{
    public void start();
}

class PetrolEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Petrol engine started");
    }
}

class DieselEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Diesel engine started");
    }
}

class ElectricEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Electric engine started");
    }
}

abstract class Vehicle{
    protected Engine engine ;

    public Vehicle(Engine engine) {
        this.engine = engine;
    }

    public abstract void start();
}

class Car extends Vehicle{

    public Car(Engine engine){
        super(engine);
    }

    @Override
    public void start(){
        System.out.println("Car is starting...");
        engine.start();
    }
}

class Bus extends Vehicle{
    public Bus(Engine engine){
        super(engine) ;
    }

    @Override
    public void start(){
        System.out.println("Bus is starting...");
        engine.start();
    }
}

class Bike extends Vehicle{
    public Bike(Engine engine){
        super(engine) ;
    }

    @Override
    public void start(){
        System.out.println("Bike is starting...");
        engine.start();
    }
}





public class Main {
    public static void main(String[] args) {
        Engine diesel = new DieselEngine() ;
        Vehicle bus = new Car(diesel) ;
        bus.start() ;
    }
}
