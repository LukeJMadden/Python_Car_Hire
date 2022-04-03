/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: MenuDisplay.
 * <P>
 * The menu display class contains 3 methods that are called by the driver class
 * to display an initial menu with data from a .csv and a secondary menu to
 * start or exit the program.
 * <P>
 * @author Luke Madden.
 * @version 1.0
 * @since 1.0
 */
package carRentalSystem;

//Import classes required from java.io package.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Import classes required from java.util package.
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//import String from driver class.
import static carRentalSystem.CarRentalTester.TEXT_RED;
import static carRentalSystem.CarRentalTester.TEXT_RESET;

//Start of Class.
public class MenuDisplay {

    //Variables created.
    private static int selection;
    private static String csvLine;
    private static String[] values;

    //Variables created and initialised with defualt values.
    private static int hireCarNum = 0;
    private static Boolean selectionLoop = true;

    //Assign value to imported scanner class to be used as helper method
    private static final Scanner INPUT = new Scanner(System.in);

    /**
     * Method to display a list of cars read from a .csv file in a menu format.
     * a. displayBreakUp helper method called from driver class throughout (a)
     * @return integer of number of hire cars available in .csv
     */
    public static int displayCarList() {

        CarRentalTester.displayBreakUp("*", 76); //a

        System.out.println("\n\t\t\tWelcome to Carrington Car Rental");

        CarRentalTester.displayBreakUp("*", 76); //a

        System.out.println("\ncars avalible for booking:");

        CarRentalTester.displayBreakUp("_", 76); //a

        System.out.printf("%-9s %-15s %-7s %-15s %-15s %-1s", "\nCar No.", "Car "
                + "Name", "Seats", "Transmission", "Car Type", "Rate/Day($)\n");

        System.out.print("-------  --------        -----   ------------    "
                + "--------        ----------\n");

        //Opens try catch statement & creates new BufferedReader object
        try {
            //@exception if .csv does not exist or contains errors.
            BufferedReader br;
            br = new BufferedReader(new FileReader("CarList.csv"));

            //Loop reads .csv and divideds .csv Strings by ','.
            while ((csvLine = br.readLine()) != null) {
                values = csvLine.split(",");
                hireCarNum++; //Adds to int tracking number of hire cars in .csv

                //Prints String from .csv, replaces symbold and puncuation.
                System.out.println(Arrays.toString(values).replace(",", "\t")
                        .replace("[", "").replace("]", "")
                        .replace("ual", "ual   "));
            }
        } //Catch file not found exception, print statement, exit system.  
        catch (FileNotFoundException ex) {
            System.out.print("The .csv file containing the car list "
                    + "seems to be missing\n\n");
            System.exit(0);

        } //Catch br.readline exception throws.
        catch (IOException ex) {
            System.out.print("The .csv file containing the car list "
                    + "seems to have errors");
            System.exit(0);
        }
        CarRentalTester.displayBreakUp("_", 76); //a

        return hireCarNum;
    }

    /**
     * Display an initial Menu that prompts the user for input to make
     * a booking or to exit from the program.
     * @return Method returns user input '1' or '2' as an Integer
     */
    public static int getSelection() {

        //If statement determines if the menu has been displayed before
        if (selectionLoop) {
            CarRentalTester.displayBreakUp("*", 41);//a

            System.out.print("\nSelect from one of the following options.\n\n"
                    + "\t1. To make a booking\n"
                    + "\t2. To exit the system\n"
                    + "\nEnter your selection: ");
            selectionLoop = false;
        }

        //Opens infinate while Loop to get correct input, will require break;
        while (true) {
            try {
                MenuDisplay.selection = INPUT.nextInt();
                if(MenuDisplay.selection == 1 || MenuDisplay.selection == 2){
                selectionLoop = true;
                break;
                }else{
                System.out.print(TEXT_RED + "Please enter a \"1\" or a "
                        + "\"2\": " + TEXT_RESET);
                INPUT.nextLine();                    
                }
            // Catches Mismatch exception if input is not an Integer
            } catch (InputMismatchException e) {
                System.out.print(TEXT_RED + "Please enter a \"1\" or a "
                        + "\"2\": " + TEXT_RESET);
                INPUT.nextLine();
            }
        }
               
        //returns int value to driver class
        return selection;
    }
    //Getter method for the numebr of hire cars avalible in th .csv file
    public static int getHireCarNum(){
        return hireCarNum;
    }
    
}//Closes class Loop
