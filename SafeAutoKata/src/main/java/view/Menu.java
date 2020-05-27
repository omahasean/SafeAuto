package view;

import java.util.Map;
import java.util.Scanner;
import model.Driver;
import model.Trip;
public class Menu {

	private Scanner menuScanner = new Scanner(System.in);

	
	public String printDataLocationMenu() {
		printBreak();
		System.out.println("Please enter the file path of the CSV file or the name if it is in the resource folder OR enter X to exit");
		printBreak();
		return menuScanner.nextLine();
	}
	
	public String printInitialMenu() {
		printBreak();		
		System.out.println("To select an option enter the character without the parentheses information in brackets is needed for task:");
		System.out.println("Enter (1) to Add a Driver with [name]");
		System.out.println("Enter (2) to Remove a Driver by [name]");
		System.out.println("Enter (3) to Add a Trip with [name, start-time, stop-time, miles-driven]");
		System.out.println("Enter (4) to Remove a Trip by [id]");
		System.out.println("Enter (5) to print the final report");
		System.out.println("Enter (X) to Exit");
		printBreak();		
		return menuScanner.nextLine();

	}
	
	public String printHandleDriverOptions() {
		printBreak();
		System.out.println("Enter the Driver name");
		printBreak();
		return menuScanner.nextLine();
	}

	public String requestStartDate() {
		printBreak();
		System.out.println("Please enter the START date and time in the format of: YYYY-MM-DD");
		System.out.println("ex:2020-05-11 for May 11th, 2020");
		printBreak();
		return menuScanner.nextLine();
	}
	public String requestStartTime() {
		printBreak();
		System.out.println("Please enter the START time in the format of: HH:mm");
		System.out.println("Time is in 24 hour format ex: 00:00 for midnight and 23:00 for 11PM");
		printBreak();
		return menuScanner.nextLine();
	}
	public String requestEndDate() {
		printBreak();
		System.out.println("Please enter the END date and time in the format of: YYYY-MM-DD");
		System.out.println("ex:2020-05-11 for May 11th, 2020");
		printBreak();
		return menuScanner.nextLine();
	}
	public String requestEndTime() {
		printBreak();
		System.out.println("Please enter the END time in the format of: HH:mm");
		System.out.println("Time is in 24 hour format ex: 00:00 for midnight and 23:00 for 11PM");
		printBreak();
		return menuScanner.nextLine();
	}

	public String requestDriverName() {
		printBreak();
		System.out.println("Please enter the name of the driver");
		printBreak();
		return menuScanner.nextLine();
	}
	public String requestMileage() {
		printBreak();
		System.out.println("Please enter the total miles for the trip to at least the first decimal place");
		System.out.println("ex: 17.3");
		printBreak();
		return menuScanner.nextLine();
	}

	public String printHandleRemoveTrip() {
		printBreak();
		System.out.println("Please enter the Trip ID");
		printBreak();
		return menuScanner.nextLine();
	}
	
	public void printHandlingRequest() {
		System.out.println("Attempting to retrieve data...");
	}
	
	public void printFileError(){
		System.out.println("File not found, please try another file name");
	}
	
	public void printResultsHeading() {
		printBreak();
		System.out.println("Driver Report");
		printBreak();
		}
	
	public void printResultForDriver(Driver driver) {
		if(driver.getTotalMiles()>0) {
			System.out.printf(driver.getName() + " drove " + driver.getTotalMiles() +" miles at an average of "+ driver.getAverageSpeed() + "\n");
		}
		else {
			System.out.println(driver.getName()+" drove 0 miles");
		}
	}
	
	public String handleEndOfProgram() {
		printBreak();
		System.out.println("Results are displayed above press X to exit program or R to restart");
		printBreak();
		
		return menuScanner.nextLine();
	}
	public void printExit() {
		printBreak();
		System.out.println("Thank you for using our program! Have a great day!");
		printBreak();
		}
	public void handleWrongChoice() {
		System.out.println("Invalid entry please try again");
	}
	private void printBreak() {
		System.out.println("============================================================================================================");
	}
}
