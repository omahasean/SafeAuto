package model;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface DataReader{

	public List<Driver> getDriversFromReport() throws FileNotFoundException;
	
	public List<Driver> getDriverTripList(List<Driver> driverList) throws FileNotFoundException;
	
}
