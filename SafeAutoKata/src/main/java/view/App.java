package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.AppController;
import model.Driver;
import model.Trip;

public class App {
	
	private Menu menu;
	private AppController controller;
	private Boolean run = true;
	
	public App(Menu menu) {
		this.menu = menu;
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		App app = new App(menu);
		app.run();
	}
	
	private void run() {
		while(run) {
			String choice = menu.printDataLocationMenu();
			if(choice.equalsIgnoreCase("x")) {
				menu.printExit();
				run = false;
			}
			else {
				try {
					controller = new AppController(choice);
					printSubMenu();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void printSubMenu() throws IOException {
		menu.printHandlingRequest();
		while(run){
			String choice = menu.printInitialMenu();
			if(choice.equals("1")) {
				String driverName = menu.printHandleDriverOptions();
				try{
					controller.addDriverToReport(driverName);
					System.out.println("Successfully added "+ driverName + " to file.");
					}
				catch(Exception e) {
					System.out.println("There was an error, please try again");
				}
			}
			else if(choice.equals("2")) {
				String driverName = menu.printHandleDriverOptions();
				try {
					controller.removeDriverFromReport(driverName);
					System.out.println("Successfully removed "+ driverName + " to file.");
				}catch(Exception e) {
					System.out.println("There was an error, please try again");
				}
			}
			else if (choice.equals("3")) {
				String driverName = menu.requestDriverName();
				String startDate = menu.requestStartDate();
				String startTime = menu.requestStartTime();
				String endDate = menu.requestEndDate();
				String endTime = menu.requestEndTime();
				String mileage = menu.requestMileage();
				Trip trip = controller.parseTripDataForRemoval(driverName, startDate, startTime, endDate, endTime, mileage);
				try {
					controller.addTripToReport(trip);
					System.out.println("Successfully added the trip to file.");
				} catch (IOException e) {
					System.out.println("There was an error, please try again");
				}
			} 
			else if (choice.equals("4")) {
				int tripId = Integer.parseInt(menu.printHandleRemoveTrip());
				controller.removeTripFromReport(tripId);
			} 
			else if (choice.equals("5")) {
				handleFinalMenu();
			} 
			else if (choice.equalsIgnoreCase("X")) {
				menu.printExit();
				run = false;
			}else {
				menu.handleWrongChoice();
			}
		}
	}
	private void handleFinalMenu() {
		while(run) {
			menu.printResultsHeading();
			List<Driver> driverList;
			try {
				driverList = controller.retrieveDataFromFile();

				for(Driver d: driverList) {
					d.calculateResults();
				}
				ArrayList<Driver> sortedList = controller.sortDriversByMilesDriven(driverList);
				for(Driver d: sortedList) {
					menu.printResultForDriver(d);
				}
			} catch (FileNotFoundException e) {
				menu.printFileError();
			}

			String choice = menu.handleEndOfProgram();
			if(choice.equalsIgnoreCase("x")) {
				run = false;
			}
			else if(choice.equalsIgnoreCase("r")) {
				run();
			}
		}
	}
}

