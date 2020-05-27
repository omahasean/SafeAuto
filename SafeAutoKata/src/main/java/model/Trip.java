package model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Trip {

	public static int ID = 1;
	private int id;
	private String driver;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double milesDriven;
	
	//for new trip creation with new ID
	public Trip(String driverName, LocalDateTime startTime, LocalDateTime endTime, double mileage) {
		this.driver=driverName;
		this.startTime = startTime;
		this.endTime=endTime;
		this.milesDriven=mileage;
		this.id = ID;
		ID++;
	}

	//for trip creation with an existing ID
	public Trip(String driverName, LocalDateTime startTime, LocalDateTime endTime, double mileage, int id) {
		this.driver=driverName;
		this.startTime = startTime;
		this.endTime=endTime;
		this.milesDriven=mileage;
		this.id = id;
	}
	
	
	public String getDriverName() {
		return driver;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public double getMilesDriven() {
		return milesDriven;
	}

	public int getId() {
		return id;
	}
	
}
