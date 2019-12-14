package TravelPackage;


public class FlightId extends TravelId {

	private int numeric;
	private String alpha;

	public FlightId(int numeric, String alpha) {
		super(numeric+""+alpha);
		this.numeric = numeric;
		this.alpha = alpha;
	}


}