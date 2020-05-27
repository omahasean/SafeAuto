
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Driver;
import model.Trip;

public class TestDriverFunctions {

	
	@Test
	public void testCalculateResultsWithValidTrips() {
		Driver test = new Driver("Dan");
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip(test.getName(), start1, end1, 30.0, 1);
		Trip trip2 = new Trip(test.getName(), start2, end2, 20.0, 2);
		test.addTripsToDriver(trip1);
		test.addTripsToDriver(trip2);
		
		test.calculateResults();
		assertTrue(test.getAverageSpeed()==50);
	}
	@Test
	public void testCalculateResultsWithOneInvalidTrip() {
		Driver test = new Driver("Dan");
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip(test.getName(), start1, end1, 30.0, 1);
		Trip trip2 = new Trip(test.getName(), start2, end2, 1.0, 2);
		test.addTripsToDriver(trip1);
		test.addTripsToDriver(trip2);
		
		test.calculateResults();
		assertTrue(test.getAverageSpeed()==60);
	}
	@Test
	public void testCalculateResultsWithNoValidTrips() {
		Driver test = new Driver("Dan");
		LocalDateTime start1 = LocalDateTime.of(2020, 3, 1, 6, 0);
		LocalDateTime end1 = LocalDateTime.of(2020, 3, 1, 6, 30);
		LocalDateTime start2 = LocalDateTime.of(2020, 3, 2, 6, 0);
		LocalDateTime end2 = LocalDateTime.of(2020, 3, 2, 6, 30);
		Trip trip1 = new Trip(test.getName(), start1, end1, 5000.0, 1);
		Trip trip2 = new Trip(test.getName(), start2, end2, 1.0, 2);
		test.addTripsToDriver(trip1);
		test.addTripsToDriver(trip2);
		
		test.calculateResults();
		assertTrue(test.getAverageSpeed()==0);
	}
}
