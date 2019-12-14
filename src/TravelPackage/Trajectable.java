package TravelPackage;

import ModelPackages.*;

public interface Trajectable {

	/**
	 * 
	 * @param arrivalDate
	 * @param arrivalTime
	 * @param departureDate
	 * @param departureTime
	 * @param source
	 * @param destination
	 * @param travelId
	 */
	ITravel createTraject(String arrivalDate, String arrivalTime, String departureDate, String departureTime, IBuilding source, IBuilding destination, TravelId travelId, java.util.List<IBuilding> sequence);

}