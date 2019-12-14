package AdminPackage;

import SectionPackage.IBoatSection;
import VehiculePackage.CruiseShip;

public class CreateBoatSectionCommand implements IAdminCommand {
    CruiseShip ship;
    IBoatSection section;

    public CreateBoatSectionCommand(CruiseShip ship, IBoatSection section) {
        this.ship = ship;
        this.section = section;
    }

    @Override
    public void execute() {
        ship.addSection(section);
    }

    @Override
    public void undo() {
        if(ship!= null){
            ship.removeSection(section);
        }
    }
}