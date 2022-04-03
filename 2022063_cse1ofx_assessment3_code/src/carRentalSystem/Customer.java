/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: Customer.
 * <P>
 * The customer class contains 1 constructor and 3 methods.
 * The Customer class is used to create object by the Car Rental Tester driver
 * class then called by the Print Booking Details class.
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Start of class
public class Customer {

    //Variables created.
    private static String customerName;
    private static String customerEmail;
    private static String customerAddress;
    
    /**
     * Constructor to create new Customer objects
     * @param customerName passed from CarRentalTester (driver) class
     * @param customerEmail passed from CarRentalTester (driver) class
     * @param customerAddress passed from CarRentalTester (driver) class
     */
    public Customer(String customerName, String customerEmail, String customerAddress) {
        Customer.customerName = customerName;
        Customer.customerEmail = customerEmail;
        Customer.customerAddress = customerAddress;
    }
    
    /**
     * Getter that returns the customer's name
     * @return String
     */
    public static String getCustomerName() {
        return customerName;
    }
    
    /**
     * Getter that returns the customer email
     * @return String
     */
    public static String getCustomerEmail() {
        return customerEmail;
    }
    
    /**
     * Getter that returns the customer's address
     * @return String
     */
    public static String getCustomerAddress() {
        return customerAddress;
    }
}
