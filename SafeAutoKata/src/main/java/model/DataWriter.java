package model;

import java.io.IOException;

public interface DataWriter {
	
	void addDriverToFile(String driverName) throws IOException;

	void addTripToFile(Trip trip) throws IOException;
	
	void removeTripFromFile(int tripId) throws IOException;
	
	void removeDriverFromFile(String driverName) throws IOException;
}
