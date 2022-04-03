/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: CarAndBookingDates.
 * <P>
 * The menu display class contains 10 methods that takes the users selection for
 * what car they wish to hire and takes the year, month, day input of both the
 * booking start and end date.
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

//Import java.time package
import java.time.LocalDate;

//Start of class
public class CarAndBookingDates {

    //Variables created.
    private static int year;
    private static int month;
    private static int day;
    private static int carChoice;
    private static int yearLoop;
    private static int monthLoop;
    private static int dayLoop;
    private static LocalDate fullBookingDate;
    private static LocalDate bookingStartDate;
    private static LocalDate bookingEndDate;
    private static String selection;
    
    //Array of Strings
    private static final List<List<String>> RECORDS = new ArrayList<>(); 

    
    //Variables created and initialised with defualt values
    private static int promptLoopCount = 0;
    private static boolean sameYearMonth = false;
    private static boolean sameYear = false;
    final private static LocalDate CURRENTDATE = LocalDate.now();
    final private static int CURRENTYEAR = CURRENTDATE.getYear();
    final private static int CURRENTMONTH = CURRENTDATE.getMonthValue();
    final private static int CURRENTDAY = CURRENTDATE.getDayOfMonth();
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    
    //Assign value to imported scanner class to be used as helper method
    private static final Scanner INPUT = new Scanner(System.in);
    
    //Initialise variables with defaults 
    public static void CarAndBookingDates() {
        CarAndBookingDates.fullBookingDate = LocalDate.of(CURRENTYEAR, 
                CURRENTMONTH, CURRENTDAY);
        CarAndBookingDates.year = CURRENTYEAR;
        CarAndBookingDates.month = CURRENTMONTH;
        CarAndBookingDates.day = CURRENTDAY;
    }

    /**
     * Method reads .csv into a List Array, displays user prompt for car 
     * selection, takes user input and returns value to driver class.
     * @return Integer value of which car number was chosen. 
     */
    public static int carSelection() {

        try (BufferedReader br = new BufferedReader(new FileReader("CarList.csv"))) {

            String line; //creates helper variable
            
            //Loop reads .csv, saves stings into array divided by ','.
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                RECORDS.add(Arrays.asList(values));
            }
        //Catch file not found exception, print statement, exit system. 
        } catch (FileNotFoundException ex) {
            System.out.print("The .csv file containing the car "
                    + "list seems to be missing");
            System.exit(0);
            
        } //Catch br.readline exception throws.
        catch (IOException ex) {
            System.out.print("The .csv file containing the car "
                    + "list seems to have errors");
            System.exit(0);
        }
        //prints prompt for user.
        System.out.print("\nTo make a booking: \n"
                + "\tSelect the car number from the car list: ");

        //creates loop to validate user input for car selection
        while (true) {
            try {
                carChoice = INPUT.nextInt();
                if (carChoice <= MenuDisplay.getHireCarNum() && carChoice > 0) {
                    break;
                } else {
                    System.out.print(TEXT_RED + "\tInvalid car selection, "
                            + "try again: " + TEXT_RESET);
                    INPUT.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.print(TEXT_RED + "\tInvalid car selection, "
                        + "try again: " + TEXT_RESET);
                INPUT.nextLine();
            }
        }
        //returns int value of what car was chosen. -1 as Array starts at 0.
        return carChoice - 1;
    }
    
    /**
     * Method calls prompt and validation methods for year, month & day.
     * Method consolidates complete and valid date for return.
     * @return LocalDate value of total booking date, start or end.
     */
    public static LocalDate getCarBookingDateFull() {

        //changes print statement pending loop amount ref from driver class
        if (CarRentalTester.loop == 0) {
            System.out.print("\n\tEnter booking start date.\n");
        } else {
            System.out.print("\n\tEnter booking end date.\n");
        }

        //controls inner loops
        while (true) {
            //Loops input for year selection; calls prompt & validation methods
            while (true) {
                try {
                    yearLoop = promptForYear();
                    promptLoopCount++;
                    if (validateYearString(Integer.toString(yearLoop))) {
                        promptLoopCount = 0;

                        //changes Boolean to assist validateMonthString method
                        if (CarAndBookingDates.year == yearLoop) {
                            sameYear = true;
                        }
                        CarAndBookingDates.year = yearLoop;

                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.print(TEXT_RED + "\t!Invalid year entry, "
                            + "try again: " + TEXT_RESET);
                    INPUT.nextLine();
                }
            }
            //Loops input for month selection; calls prompt & validation methods 
            while (true) {
                try {
                    monthLoop = promptForMonth();
                    promptLoopCount++;

                    if (validateMonthString(Integer.toString(monthLoop))) {
                        promptLoopCount = 0;
                        CarAndBookingDates.month = monthLoop;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.print(TEXT_RED + "\t!Invalid month entry, "
                            + "try again: " + TEXT_RESET);
                    INPUT.nextLine();
                }
            }
            //Loops input for day selection; calls prompt & validation methods
            while (true) {
                try {
                    dayLoop = promptForDay();
                    promptLoopCount++;
                    if (validateDayString(Integer.toString(dayLoop))) {
                        promptLoopCount = 0;
                        CarAndBookingDates.day = dayLoop;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.print(TEXT_RED + "\t!Invalid day entry, "
                            + "try again: " + TEXT_RESET);
                    INPUT.nextLine();
                }
            }
            //Resets Booleans that control loops and prompt functions
            sameYearMonth = false;
            sameYear = false;
            //concatonates user input into a complete LocalDate value.
            fullBookingDate = LocalDate.of(CarAndBookingDates.year, 
                    CarAndBookingDates.month, CarAndBookingDates.day);
            return fullBookingDate;

        }
    }
    
    /**
     * Method displays a prompt for user year input and returns input.
     * @return Integer input from user.
     * @throws InputMismatchException to calling method.
     */
    public static int promptForYear() throws InputMismatchException {
        promptLoopCount++;
        if (promptLoopCount <= 1) {
            System.out.print("\tPlease enter the year - "
                    + "for example '2021': ");
        }
        int promptYearLoop = INPUT.nextInt();
        return promptYearLoop;
    }
    
    /**
     * Method displays a prompt for user month input and returns input.
     * @return Integer input from user.
     * @throws InputMismatchException to calling method.
     */
    public static int promptForMonth() throws InputMismatchException {

        promptLoopCount++;
        if (promptLoopCount <= 1) {
            System.out.print("\tPlease enter the month number"
                    + " - for example '6': ");
        }
        int promptMonthLoop = INPUT.nextInt();
        return promptMonthLoop;
    }
    
    /**
     * Method displays a prompt for user day input and returns input.
     * @return Integer input from user.
     * @throws InputMismatchException to calling method.
     */
    public static int promptForDay() {
        promptLoopCount++;
        if (promptLoopCount <= 1) {
            System.out.print("\tPlease enter the day number"
                    + " - for example '21': ");
        }
        int promptDayLoop = INPUT.nextInt();
        return promptDayLoop;
    }

    /**
     * Method validates year input against current year.
     * @param year the string to be validated.
     * @return Boolean to confirm if input is valid or not.
     * @throws InputMismatchException to calling method.
     */
    public static boolean validateYearString(String year)
            throws InputMismatchException {
        
        //helper variable created, converts string input to an integer 
        int yearInt = Integer.parseInt(year);
        while (true) {
            if (yearInt >= CarAndBookingDates.year 
                    && yearInt < (CURRENTYEAR + 10)) {
                return true;
            } else {
                System.out.print(TEXT_RED + "\t!Invalid year entry, "
                        + "try again: " + TEXT_RESET);
                return false;
            }
        }
    }
    
    /**
     * Method validates month input against current month if the year is
     * the same or just ensure the integer is within range.
     * @param month the string to be validated.
     * @return Boolean to confirm if input is valid or not.
     * @throws InputMismatchException to calling method.
     */
    public static boolean validateMonthString(String month)
            throws InputMismatchException {
        
        //helper variable created, converts string input to an integer 
        int monthInt = Integer.parseInt(month);
        while (true) {
            if (sameYear) {
                if (monthInt < CarAndBookingDates.month) {
                    System.out.print(TEXT_RED + "\t!Invalid month entry, "
                            + "try again: " + TEXT_RESET);
                    return false;
                //changes Boolean to assist validateDayString method
                } else if (monthInt == CarAndBookingDates.month) {
                    CarAndBookingDates.sameYearMonth = true;
                    return true;
                } else {
                    return true;
                }
            } else if (monthInt > 0 && monthInt < 13) {
                return true;
            } else {
                System.out.print(TEXT_RED + "\t!Invalid month entry, "
                        + "try again: " + TEXT_RESET);
                return false;
            }
        }
    }
    
    /**
     * Method confirms how many days are within each month based of year,
     * including leap years. Then validates day input against current day if 
     * the year & month are the same or just ensure the integer is within 
     * range of the days within each month.
     * @param day the string to be validated.
     * @return Boolean to confirm if input is valid or not.
     * @throws InputMismatchException to calling method.
     */
    public static boolean validateDayString(String day)
            throws InputMismatchException {
        
        //helper variable created, converts string input to an integer.
        int dayInt = Integer.parseInt(day);
        //helper variable created for how many days are within each month.
        int numberOfDays = 0;

        while (true) {
            switch (CarAndBookingDates.month) {
                case 1:
                case 3:
                case 6:
                case 7:
                case 8:
                case 10:
                case 12:
                    numberOfDays = 31;
                    break;
                //Leap year calculations occur.
                case 2:
                    if (CarAndBookingDates.year % 100 == 0) {
                        if (CarAndBookingDates.year % 400 == 0) {
                            numberOfDays = 29;
                        }
                    } else if (CarAndBookingDates.year % 4 == 0) {
                        numberOfDays = 29;
                    } else {
                        numberOfDays = 28;
                    }
                    break;
                case 4:
                case 5:
                case 9:
                case 11:
                    numberOfDays = 30;
                    break;
            }
            //user input validated against all posibilitis.
            if (dayInt <= numberOfDays && dayInt > 0) {
                if (sameYearMonth) {
                    if (CarRentalTester.loop == 1) {
                        if (dayInt > CarAndBookingDates.day) {
                            return true;
                        } else {
                            System.out.print(TEXT_RED + "\t!Invalid day entry, "
                                    + "please enter a day after the booking "
                                    + "start date: " + TEXT_RESET);
                            return false;
                        }
                    } else if (dayInt >= CURRENTDAY) {
                        return true;
                    } else {
                        System.out.print(TEXT_RED + "\t!Invalid day entry, "
                                + "try again: " + TEXT_RESET);
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                System.out.print(TEXT_RED + "\t!Invalid day entry, "
                        + "try again: " + TEXT_RESET);
                return false;
            }
        }

    }

}
