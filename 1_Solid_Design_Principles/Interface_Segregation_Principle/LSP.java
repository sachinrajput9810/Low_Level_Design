// Many client specific interfaces are better than one general-purpose interface. (Interface Segregation Principle)
public class LSP{
    public static void main(String[] args) {
        TwoDShape square = new Square(4);
        square.area();
        TwoDShape circle = new Circle(7);
        circle.area();
        ThreeDShape cube = new Cube(5);
        cube.area();
        cube.volume();

    }

    public interface TwoDShape {
        public void area();
    }

    public interface ThreeDShape {
        public void area();
        public void volume();
    }

    public static class Square implements TwoDShape {
        private int side;
        public Square(int side) {
            this.side = side;
        }
        @Override
        public void area() {
            System.out.println("Calculating area of Square :: " + side * side);
        }
    }
    public static class Circle implements TwoDShape {
        private int radius;
        public Circle(int radius) {
            this.radius = radius;
        }
        @Override
        public void area() {
            System.out.println("Calculating area of Circle :: " + 2*3.14*radius);
        }
    }
    public static class Cube implements ThreeDShape {
        private int side;
        public Cube(int side) {
            this.side = side;
        }
        @Override
        public void area() {
            System.out.println("Calculating area of Cube :: " + 6 * side * side);
        }

        @Override
        public void volume() {
            System.out.println("Calculating volume of Cube :: " + side * side * side);
        }
    }
}