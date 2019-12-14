package TravelPackage;

import ModelPackages.Port;
import VehiculePackage.CruiseShip;

import java.util.List;

public class Cruise extends Cruisable {

    protected Port source;
    protected Port destination;
    protected java.util.List<Port> cruiseSequence;
    protected CruiseShip boat;
    @Override
    public CruiseShip getBoat() {
        return boat;
    }

    @Override
    public List<Port> getSequence() {
        return this.cruiseSequence;
    }

    @Override
    public void setSequence(List<Port> sequence) {
        this.cruiseSequence = sequence;
    }

    @Override
    public void setBoat(CruiseShip cruiseShip) {
        this.boat = cruiseShip;
    }

}