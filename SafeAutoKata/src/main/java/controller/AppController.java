package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.DataReader;
import model.DataWriter;
import model.Driver;
import model.ReportReader;
import model.ReportWriter;
import model.Trip;

public class AppController {

	private DataReader reader;
	private DataWriter writer;
	
	public AppController(String filePath) throws FileNotFoundException{
		reader = new ReportReader(filePath);
		writer = new ReportWriter(filePath);
	}
	
	public List<Driver> retrieveDataFromFile() throws FileNotFoundException{
		List<Driver> driverList =  reader.getDriversFromReport();
		List<Driver> updatedList = reader.getDriverTripList(driverList);
		return updatedList;
	}
	
	public void addDriverToReport(String driverName) throws IOException {
		writer.addDriverToFile(driverName);
	}
	
	public void removeDriverFromReport(String driverName) throws IOException {
		writer.removeDriverFromFile(driverName);
	}
	public void addTripToReport(Trip trip) throws IOException {
		writer.addTripToFile(trip);
	}
	public void removeTripFromReport(int tripId) throws IOException {
		writer.removeTripFromFile(tripId);
	}
	public Trip parseTripDataForRemoval(String driverName, String startDate, String startTime, String endDate, String endTime, String mileage) {
		LocalDate dateStart = LocalDate.parse(startDate);
		LocalDate dateEnd = LocalDate.parse(endDate);
		LocalTime timeStart = LocalTime.parse(startTime);
		LocalTime timeEnd = LocalTime.parse(endTime);
		LocalDateTime start = LocalDateTime.of(dateStart, timeStart);
		LocalDateTime end = LocalDateTime.of(dateEnd, timeEnd);
		double miles = Double.parseDouble(mileage);
		return new Trip(driverName, start, end, miles, Trip.ID);
	}
	
	public ArrayList<Driver> sortDriversByMilesDriven(List<Driver> unsortedList){
		unsortedList.sort(Comparator.comparingDouble(driver -> driver.getTotalMiles()));
		ArrayList<Driver> sortedList = new ArrayList<Driver>();
		for(int i = unsortedList.size()-1; i>-1; i--) {
			sortedList.add(unsortedList.get(i));
		}
		return sortedList;
	}
}
