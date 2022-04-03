/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: PrintBookingDetails.
 * <P>
 * The Car class contains 1 constructor.
 * The Car Class is the parent class for car objects and is extended 
 * to PremiumCar its only child-class.
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Start of class
public class PrintBookingDetails {
    
    /**
     * Constructor that takes carBooking and Customer object date to print
     * a final display to user with a summary of their booking.
     * a. displayBreakUp helper method called from driver class throughout (a)
     * @param carBooking passed from CarRentalTester (Driver) class
     * @param customer passed from CarRentalTester (Driver) class
     */
    public static void PrintBookingDetails(CarBooking carBooking, Customer customer){
        
        System.out.print("\n");
        CarRentalTester.displayBreakUp("-", 76); //a.
        
        //Prints details from the passed customer object.
        System.out.print("\n\tThanks for your booking " + customer.getCustomerName());
        System.out.printf("\n\tEmail   %45s", customer.getCustomerEmail());
        System.out.printf("\n\tAddress %45s", customer.getCustomerAddress());
        
        System.out.print("\n\t");
        CarRentalTester.displayBreakUp(".", 58); //a.
        System.out.print("\n\n\tBooking Confirmed\n\n\tHere's your booking summary\n\t");
        CarRentalTester.displayBreakUp(".", 58);
        
        //Prints details from carBooking object.
        System.out.printf("\n\tCar Selected %45s", carBooking.getCarName());
        System.out.printf("\n\tStart date   %45s", carBooking.getStartDate());
        System.out.printf("\n\tEnd date     %45s", carBooking.getEndDate());
        System.out.printf("\n\tNumber of days booked %36s", carBooking.getTotalDays());
        System.out.printf("\n\tCar rate per day %41s", carBooking.getNewRate());
        System.out.print("\n\t");
        CarRentalTester.displayBreakUp("_", 58); //a.
        System.out.printf("\n\tTotal cost   %45s", carBooking.getCost());
        System.out.print("\n");
        CarRentalTester.displayBreakUp("-", 76); //a.
        System.out.print("\n\n");
    }
    
    
}
