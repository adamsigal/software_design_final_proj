package TravelPackage;

import ModelPackages.*;
import VehiculePackage.*;

public abstract class Flightable extends ITravel {

	public abstract Plane getPlane();
	public abstract void setPlane(Plane plane);

}