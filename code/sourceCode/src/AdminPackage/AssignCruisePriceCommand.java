package AdminPackage;


import TravelPackage.Cruise;
import TravelPackage.CruiseId;
import TravelPackage.TravelId;
import TravelPackage.TravelRegister;
import VehiculePackage.CruiseShip;

public class AssignCruisePriceCommand implements IAdminCommand {
    float oldPrice;
    float newPrice;
    CruiseId modifiedTravel;

    public AssignCruisePriceCommand(CruiseId cruiseId, float np) {
        modifiedTravel = cruiseId;
        newPrice = np;
    }

    @Override
    public void execute() {

        CruiseShip cruiseShip = ((Cruise)TravelRegister.getInstance().get(modifiedTravel)).getBoat();

        oldPrice = cruiseShip.getBasePrice();
        cruiseShip.setPrice(newPrice);
    }

    @Override
    public void undo() {

        if (oldPrice != 0f && modifiedTravel != null) {
            CruiseShip cruiseShip = ((Cruise)TravelRegister.getInstance().get(modifiedTravel)).getBoat();

            cruiseShip.setPrice(oldPrice);

            oldPrice = 0f;
            modifiedTravel = null;
        }
    }


}