import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import model.Driver;
import model.ReportReader;
import model.ReportWriter;
import model.Trip;

public class TestReportWriter {

	@Test
	public void testAddDriverToFile() throws FileNotFoundException {
		ReportWriter writer = new ReportWriter("testOut.csv");
		ReportReader reader = new ReportReader("testOut.csv");
		try {
			writer.addDriverToFile("Sean");
			writer.addDriverToFile("Ben");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Driver> driverList = reader.getDriversFromReport();
		
		assertEquals(true, driverList.size() > 0);
		
		//Assert.assertTrue(driverList.size()>0);
	}
	@Test
	public void testAddTripToFile() throws FileNotFoundException {
		ReportWriter writer = new ReportWriter("testOut.csv");
		ReportReader reader = new ReportReader("testOut.csv");
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip("Sean", start1, end1, 30.0, 1);
		Trip trip2 = new Trip("Ben", start2, end2, 20.0, 2);
		
		try {
			writer.addTripToFile(trip1);
			writer.addTripToFile(trip2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Driver> driverList = reader.getDriversFromReport();
		List<Driver> updatedList = reader.getDriverTripList(driverList);
		Driver test = updatedList.get(0);
		
		Assert.assertTrue(test.getDriverTrips().size()>0);
	}
	
	// TODO: put test back in.
	public void testRemoveTripFromFile() throws FileNotFoundException {
		ReportWriter writer = new ReportWriter("testOut.csv");
		ReportReader reader = new ReportReader("testOut.csv");
		try {
			writer.addDriverToFile("Sean");
			writer.addDriverToFile("Ben");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip("Sean", start1, end1, 30.0, 1);
		Trip trip2 = new Trip("Ben", start2, end2, 20.0, 2);
		
		try {
			writer.addTripToFile(trip1);
			writer.addTripToFile(trip2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.removeTripFromFile(2);
		}catch(IOException e) {
			e.printStackTrace();
		}
		List<Driver> driverList = reader.getDriversFromReport();
		List<Driver> updatedList = reader.getDriverTripList(driverList);
		
		Driver ben = updatedList.get(1);
		Assert.assertTrue(ben.getDriverTrips().size()==0);
	}
	
	// TODO: put test back in.
	public void testRemoveDriverFromFile() {
		ReportWriter writer = new ReportWriter("testOut.csv");
		ReportReader reader = new ReportReader("testOut.csv");
		try {
			writer.addDriverToFile("Sean");
			writer.addDriverToFile("Ben");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip("Sean", start1, end1, 30.0, 1);
		Trip trip2 = new Trip("Ben", start2, end2, 20.0, 2);	
		try {
			writer.addTripToFile(trip1);
			writer.addTripToFile(trip2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.removeDriverFromFile("Ben");
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
		List<Driver> driverList = reader.getDriversFromReport();
		Assert.assertTrue(driverList.size()==1);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	
}
