package AdminPackage;

import TravelPackage.*;
import VehiculePackage.IVehicule;
import VehiculePackage.Plane;

import java.util.ArrayList;

public class AssignFlightPriceCommand implements IAdminCommand {
    float oldPrice;
    float newPrice;
    FlightId modifiedTravel;

    public AssignFlightPriceCommand(FlightId mt, float np) {
        modifiedTravel = mt;
        newPrice = np;
    }

    @Override
    public void execute() {
        TravelRegister travelRegister= TravelRegister.getInstance();
        Flight flight = (Flight) (TravelRegister.getInstance().get(modifiedTravel));
        Plane plane = ((Flight)(TravelRegister.getInstance().get(modifiedTravel))).getPlane();

        oldPrice = plane.getBasePrice();
        plane.setPrice(newPrice);

    }


    @Override
    public void undo() {
        if (oldPrice != 0f && modifiedTravel != null) {
            Plane plane = ((Flight)TravelRegister.getInstance().get(modifiedTravel)).getPlane();

            plane.setPrice(oldPrice);

            oldPrice = 0f;
            modifiedTravel = null;
        }
    }
}