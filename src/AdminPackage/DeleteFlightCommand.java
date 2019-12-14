package AdminPackage;

import TravelPackage.Flight;
import TravelPackage.TravelRegister;

public class DeleteFlightCommand implements IAdminCommand {
    public DeleteFlightCommand(Flight deletedFlight) {
        this.deletedFlight = deletedFlight;
    }

    Flight deletedFlight;

    @Override
    public void execute() {
        TravelRegister.getInstance().delete(deletedFlight.getTravelId());
    }

    @Override
    public void undo() {

    }
}