package AdminPackage;

import TravelPackage.Cruise;
import TravelPackage.TravelRegister;

public class DeleteCruiseCommand implements IAdminCommand {
    Cruise deletedCruise;

    public DeleteCruiseCommand(Cruise deletedCruise) {
        this.deletedCruise = deletedCruise;
    }

    @Override
    public void execute() {
        TravelRegister.getInstance().delete(deletedCruise.getTravelId());

    }

    @Override
    public void undo() {
        TravelRegister.getInstance().add(deletedCruise);
    }
}