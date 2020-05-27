import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Driver;
import model.ReportReader;
import model.ReportWriter;
import model.Trip;

public class TestReportReader {

	
	@Test
	public void testReadAnyDataFromFile() throws FileNotFoundException {
		ReportReader reader = new ReportReader("test.csv");
		List<Driver> driverList = reader.getDriversFromReport();

		
		Assert.assertTrue(driverList.size()>0);
	}
	
	@Test
	public void testDataIsPopulatedInObject() throws FileNotFoundException {
		ReportReader reader = new ReportReader("test.csv");
		List<Driver> driverList = reader.getDriversFromReport();
		List<Driver> updatedList = reader.getDriverTripList(driverList);
		Driver testDriver = updatedList.get(0);
		Map<Integer, Trip> testTripList = testDriver.getDriverTrips();
		Assert.assertTrue(testTripList.size()>0);
	}
}
