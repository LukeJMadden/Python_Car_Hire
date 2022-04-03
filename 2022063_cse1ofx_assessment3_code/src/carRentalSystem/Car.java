/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: Car.
 * <P>
 * The Car class contains 1 constructor and 2 methods.
 * The Car Class is the parent class for car objects and is extended 
 * to PremiumCar its only child-class.
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Start of class
public class Car {

    //Variables created.
    protected static double carRate;
    protected static String carName;

    /**
     * Constructor to create new Car objects
     * @param carName passed from carBooking class
     * @param carRate passed from carBooking class
     */
    public Car(String carName, double carRate) {
        Car.carName = carName;
        Car.carRate = carRate;
    }
    /**
     * Getter that returns the car Rate
     * @return double
     */
    public double getCarRate() {
        return carRate;
    }
    /**
     * Getter that returns car Name
     * @return String
     */
    public String getCarName() {
        return carName;
    }

}
