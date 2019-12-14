package TravelPackage;

public interface Visitor {

	/**
	 * 
	 * @param travel
	 */
	String visit(ITravel travel, char classe);

}