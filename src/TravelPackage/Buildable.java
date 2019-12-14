package TravelPackage;

import ModelPackages.*;

public interface Buildable {

	/**
	 * 
	 * @param buildingID
	 * @param city
	 */
	IBuilding createBuilding(String buildingID, String city);

}