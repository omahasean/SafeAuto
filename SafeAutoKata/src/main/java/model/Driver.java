package model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.time.temporal.ChronoUnit;

public class Driver {

	private String name;
	private Map<Integer, Trip> driverTrips;
	private double averageSpeed = 0;
	private double totalMiles = 0;

	public Driver(String name) {
		this.name = name;
		this.driverTrips = new TreeMap<Integer, Trip>();
	}

	public String getName() {
		return name;
	}

	public Map<Integer, Trip> getDriverTrips() {
		return driverTrips;
	}

	public void addTripsToDriver(Trip trip) {
		driverTrips.put(trip.getId(), trip);
	}

	public double getAverageSpeed() {
		return averageSpeed;
	}

	private void setAverageSpeed(double speed) {
		this.averageSpeed = speed;
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public void setTotalMiles(double totalMiles) {
		this.totalMiles = totalMiles;
	}

	/*
	 * custom methods below
	 * 
	 * 
	 * takes the list associated to driver and calculates average speed and total
	 * miles driven.
	 */
	public void calculateResults() {
		Map<Integer, Trip> tripList = getDriverTrips();
		long totalAverageSpeed = 0;
		double averageSpeed = 0;
		long totalMilage = 0;
		int validTripCount = 0;
		Set<Integer> tripKeys = tripList.keySet();
		int iterationTracker = 0;
		for (Integer id : tripKeys) {
			Trip t = tripList.get(id);
			iterationTracker++;

			if (t.getStartTime().isBefore(t.getEndTime())) {
				double totalMiles = t.getMilesDriven();
				double totalMinutes = ChronoUnit.MINUTES.between(t.getStartTime(), t.getEndTime());
				if(totalMinutes!=0 && totalMiles!=0) {
					averageSpeed = calculateAverageSpeed(totalMiles, totalMinutes);
				}			
				if (averageSpeed > 5 && averageSpeed < 100) {
					totalAverageSpeed += averageSpeed;
					totalMilage += t.getMilesDriven();
					validTripCount++;
				}
			}


		}
		if (validTripCount == 0 && iterationTracker == tripKeys.size()) {
			setAverageSpeed(0);
			setTotalMiles(0);
		}
		else {
			setAverageSpeed(totalAverageSpeed / validTripCount);
			setTotalMiles(totalMilage);
		}
	}
	
	/*abstracted method to calculate average speed, I figure in a real life application
	 * this may be more complex in terms of determining the risk a driver poses
	 * and more may go into calculating that. In the future this would be replaced or added on to.
	 * */
	private double calculateAverageSpeed(double totalMiles, double totalMinutes) {
		return ((totalMiles / totalMinutes) * 60);
	}
}
