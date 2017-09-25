package assignable;

public class Assignable {
    public static void main(String[] args) {
        Car car = Car.createNewCar(Volvo.class);
        if (!car.start()) {
            System.out.println("   total Swedish meatball.");
        }
        
        car = Car.createNewCar(Yugo.class);
        if (!car.start()) {
            System.out.println("    Get a Volvo.");
        }
    }
}
