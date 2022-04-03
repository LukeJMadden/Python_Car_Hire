/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: CarRentalTester (Driver class)
 * <P>
 * The CarRentalTester class contains 1 Main method and 2 methods.
 *
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Import java.time package
import java.time.LocalDate;

//Import classes required from java.util package.
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Start of class
public class CarRentalTester {

    //Variables created.
    private static String fullName;
    private static String email;
    private static String address;
    private static int carChoice;
    private static int menuChoice;
    private static int hireCarNum;
    private static LocalDate startBookingDate;
    private static LocalDate endBookingDate;

    /**
     * Variable for class access to change test color to red
     */
    public static final String TEXT_RED = "\u001B[31m";
    /**
     * Variable for class access to change test color to black
     */
    public static final String TEXT_RESET = "\u001B[0m";
    /**
     * Variable for class access to know how many iteration have occured.
     * 0 = first loop, 1 = second loop.
     */
    public static int loop = 0;

    //Assign value to imported scanner class to be used as helper method
    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * Main method for package, calls required subclass methods.
     * @param args
     */
    public static void main(String[] args) {

        loop = 0;
        hireCarNum = MenuDisplay.displayCarList();

        //prints the number of hire cars within .csv file & informs user of 
        //additional fee for premium cars.
        System.out.print("\nTotal " + (hireCarNum) + " cars available.");
        System.out.println("\n\n**Note for premium cars, there's additional 5% "
                + "insurance access applied \n  to the car rate.\n");

        //Primary menu loop open, infinate loop without break;
        while (true) {
            /*
            * Calls getSelection method from MenuDisplay class for user 
            * prompt and input to continue program or exit
            */
            menuChoice = MenuDisplay.getSelection();

            /*
             * If user selects '1' the program is executed calling menu prompts
             * and assigning user input from called methods to variables.
             * Car selected, start and end booking dates are reciecved. 
             */
            if (menuChoice == 1) {
                carChoice = CarAndBookingDates.carSelection();
                CarAndBookingDates.CarAndBookingDates();
                startBookingDate = CarAndBookingDates.getCarBookingDateFull();
                loop++;
                endBookingDate = CarAndBookingDates.getCarBookingDateFull();

                //Nested while loop to prompt/validate and get customer details
                while (true) {

                    System.out.print("\n\tYour Full name: ");
                    fullName = INPUT.nextLine();

                    System.out.print("\tYour Email: ");
                    
                    //nested while loop for email validation
                    while (true) {
                        String emailLoop = INPUT.next();
                        if (CarRentalTester.emailValidator(emailLoop)) {
                            CarRentalTester.email = emailLoop;
                            break;
                        }
                    }

                    System.out.print("\tYour residential address: ");
                    INPUT.nextLine();
                    address = INPUT.nextLine();
                    
                    //New customer object created
                    Customer customerDetails;
                    customerDetails = new Customer(fullName, email, address);
                    
                    //New CarBooking object created
                    CarBooking customerBooking;
                    customerBooking = new CarBooking(startBookingDate, 
                            endBookingDate, carChoice);
                    
                    //makeBooking method called from customerBooking class
                    customerBooking.makeBooking();
                    
                    //Final booking print of order summary, objectes passed
                    PrintBookingDetails.PrintBookingDetails(customerBooking, 
                            customerDetails);
                    break;//Nested loop broken out of
                }
            //Exits prggram is '2' is selected
            } else {
                System.out.println("\nThank you for using our hire car service, goodbye!");
                System.exit(0);

            }

        }//Primary Menu Loop close

    }//Driver class close

    /**
     * Helper Method called by most menu display classes.
     * @param type can be any String that requires to be printer 'amount' times
     * @param amount any int, this will print type this many times.
     */
    public static void displayBreakUp(String type, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(type);
        }

    }

    /**
     * Basic Email validator that checks input from main method/driver class
     * @param emailInput
     * @return Boolean if email is valid or not
     */
    public static boolean emailValidator(String emailInput) {
        while (true) {
            String emailValidation = emailInput;

            //initialize the Pattern object
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);

            //searching for occurrences of regex
            Matcher matcher = pattern.matcher(emailValidation);
            boolean matches = matcher.matches();
            if (matches) {
                return true;
            } else {
                System.out.print(TEXT_RED + "\tPlease enter a valid email "
                        + "(e.g janeDoe@gmail.com): " + TEXT_RESET);
                return false;
            }

        }
    }

}
