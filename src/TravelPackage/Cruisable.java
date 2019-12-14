package TravelPackage;

import ModelPackages.*;
import VehiculePackage.*;

public abstract class Cruisable extends ITravel {

	public abstract CruiseShip getBoat();
	public abstract void setBoat(CruiseShip cruiseShip);
	public abstract java.util.List<Port> getSequence();
	public abstract void setSequence(java.util.List<Port> sequence);

}