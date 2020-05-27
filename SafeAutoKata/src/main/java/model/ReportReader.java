package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ReportReader implements DataReader {

	private File file;

	// @AutoWired
	public ReportReader(String fileName) {
		file = new File(fileName);
	}
	
	//gets list of Drivers seperate in case drivers are listed below trips
	public List<Driver> getDriversFromReport() throws FileNotFoundException {
		List<Driver> driverList = new ArrayList<Driver>();
		try (Scanner tripScanner = new Scanner(file)) {
			while (tripScanner.hasNextLine()) {
				String nextLine = tripScanner.nextLine();
				String[] tripFields = nextLine.split(",");
				String type = tripFields[0];
				if (type.equals("Driver")) {
					String name = tripFields[1];
					Driver driver = new Driver(name);
					driverList.add(driver);
				}
			}
		}
		return driverList;
	}

	//gets all Trips and then assigns them to drivers and returns populated items;
	public List<Driver> getDriverTripList(List<Driver> driverList) {
		try (Scanner tripScanner = new Scanner(file)) {
			while (tripScanner.hasNextLine()) {
				String nextLine = tripScanner.nextLine();
				String[] tripFields = nextLine.split(",");
				String type = tripFields[0];
				if (type.equals("Trip")) {
					int id = Integer.parseInt(tripFields[1]);
					String name = tripFields[2];
					String start = tripFields[3];
					String end = tripFields[4];
					double mileage = Double.parseDouble(tripFields[5]);
					LocalDateTime startDateTime = parseLocalDateTimeFromString(start);
					LocalDateTime endDateTime = parseLocalDateTimeFromString(end);
					if (id > Trip.ID) {
						Trip.ID = id + 1;
					}
					for (Driver driver : driverList) {
						if (name.equals(driver.getName())) {
							Trip trip = new Trip(name, startDateTime, endDateTime, mileage, id);
							driver.addTripsToDriver(trip);
						}
					}
				}
			}
			for (Driver d : driverList) {
				d.calculateResults();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driverList;
	}


	/*
	 * This takes the fields inside of the split for Date and Time due to some
	 * issues I was having with the native LocalTime.parse(charSeq) I created this,
	 * which while a little sloppier and requires this exact input allows parsing
	 * for this particular date time format
	 */
	private LocalDateTime parseLocalDateTimeFromString(String dateTime) {
		String[] fields = dateTime.split("-");
		int dayOfMonth = Integer.parseInt(fields[1]);
		int month = Integer.parseInt(fields[0]);
		int year = Integer.parseInt(fields[2]);
		String[] timeFields = fields[3].split(":");
		int hour = Integer.parseInt(timeFields[0]);
		int minute = Integer.parseInt(timeFields[1]);
		LocalDate date = LocalDate.of(year, month, dayOfMonth);
		LocalTime time = LocalTime.of(hour, minute);

		return LocalDateTime.of(date, time);
	}
}
