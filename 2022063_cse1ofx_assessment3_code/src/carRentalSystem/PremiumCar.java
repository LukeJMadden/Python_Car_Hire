/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: PremiumCar.
 * <P>
 * The Car class contains 1 constructor and 1 methods.
 * The Car Class is the child-class and extends from the Car class
 * PremiumCar is the only child-class.
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Start of class
public class PremiumCar extends Car{
    
    //Create and inisialize final variable for the high rate.
    private static final double INSURANCERATE = 0.05;
    
    /**
     * Constructor to create new PremiumCar objects
     * @param carRate
     */
    public PremiumCar(double carRate) {
        super(carName, carRate);
    }
    /**
     * Getter that overrides Car Getter with new Premium car rate.
     * @return double
     * @Override getCarRate method from Car class
     */
    @Override
    public double getCarRate(){
    carRate = carRate + (carRate * INSURANCERATE);    
    return carRate;
}
    
    
}
