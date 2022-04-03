/**
 * Program name: Car Rental System.
 * Package: carRentalSystem.
 * Class: CarBoooking
 * <P>
 * The menu CarBoooking class contains 11 methods that reads a .csv file into
 * a 2D array, defines key variables, creates new car objects and calculates
 * the total number of booking days and the associated cost.
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
//Import java.time 
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//Import classes required from java.util package.
import java.util.ArrayList;
import java.util.List;

//Start of class
public class CarBooking {
    
    //Variables created.
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static int carNumber;
    private static long totalDays;
    private static double newRate;
    private static double cost;
    private static String carName;
    
    //New car object created with defualt values
    private static Car car = new Car(null, 0.0);

    /**
     * Constructor that initialize variables with input from driver class
     * @param startDate is the start date of the car booking
     * @param endDate is the end date of the car booking
     * @param carNumber is the car number chosen by user
     */
    public CarBooking(LocalDate startDate, LocalDate endDate, int carNumber) {
        CarBooking.startDate = startDate;
        CarBooking.endDate = endDate;
        CarBooking.carNumber = carNumber;
    }

    /**
     * Make Booking method reads .csv file and inputs Strings into Array then
     * into a 2d array to re-create the .csv rows and columns.
     * The name of the Car and daily rate are defined and car object updated
     * according to if the car is Premium or Standard.
     * The make booking method also calls the calculateTotalDays & calculateCost
     * methods passing data from method.
     */
    public void makeBooking() {
     List<List<String>> list = new ArrayList<List<String>>();    
        try {
            String fileName = "CarList.csv";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            String[] headers = line.split(",");
            for (String header : headers) {
                List<String> subList = new ArrayList<String>();
                subList.add(header);
                list.add(subList);
            }
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(",");
                for (int i = 0; i < elems.length; i++) {
                    list.get(i).add(elems[i]);
                }
            }
            //convert array into 2d array 'arrayy2D'.
            br.close();
            int rows = list.size();
            int cols = list.get(0).size();
            String[][] array2D = new String[rows][cols];
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    array2D[row][col] = list.get(row).get(col);
                }          
            }
            
            //gets car name and car rate from 2d array 'arrayy2D'.
            carName = array2D[1][carNumber];
            newRate = Double.parseDouble(array2D[5][carNumber]);                
           
            //updates car object with a name and daily cost rate
            CarBooking.car = new Car(carName, newRate);
            
            /*
            * checks to see if 'arrayy2D' car selection is a Premium car and
            * if it is, updates the car object with a new preium rate by calling
            * the extended class 'PremiumCar'.
            */
            if(array2D[4][carNumber].contains("Premium")){   
            CarBooking.car = new PremiumCar(newRate);
            newRate = car.getCarRate();
                
            }   
            //call methods to calculate total days for car hire and cost.
            CarBooking.calculateTotalDays();    
            CarBooking.calculateCost(newRate, totalDays);                    
            
        } 
        catch (FileNotFoundException ex) {
            System.out.print("The .csv file containing the car "
                    + "list seems to be missing");
            System.exit(10);
        } //Catch br.readline exception throws
        catch (IOException ex) {
            System.out.print("The .csv file containing the car "
                    + "list seems to have errors");
            System.exit(10);
        }
    }
    
    /**
     * Calculates total amount of hire days, called from makeBooking method.
     */
    public static void calculateTotalDays() {
        totalDays = ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * Calculates cost of car hire, called from makeBooking method
     * @param newRate passed from makeBooking method
     * @param totalDays from makeBooking method (calculateTotalDays method)
     */
    public static void calculateCost(double newRate, long totalDays) {
        cost = newRate * totalDays;
    }
    
    /**
     * Getter method to get total cost of car hire.
     * @return double 
     */
    public double getCost() {
        return cost;
    }
    
    /**
     * Getter method to get the car hire start date.
     * @return LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * Getter method to get car hire end date.
     * @return LocalDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Getter method to get total days of car hire.
     * @return long
     */    
    public long getTotalDays() {
        return totalDays;
    }

    /**
     * Getter method to get selected car number.
     * @return int
     */    
    public int getCarNumber() {
        return carNumber;
    }
    
    /**
     * Getter method for new car rate pending Premium or Standard.
     * @return double
     */
    public double getNewRate() {        
        return newRate;
    }
    
    /**
     * Getter method for the name of the car.
     * @return string
     */
    public String getCarName() {
        return carName;
    }
}


