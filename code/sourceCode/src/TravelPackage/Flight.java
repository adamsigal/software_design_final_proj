package TravelPackage;

import ModelPackages.Airport;
import VehiculePackage.Plane;

public class Flight extends Flightable {

    protected Plane plane;

    @Override
    public Plane getPlane() {
        return plane;
    }

    @Override
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

}