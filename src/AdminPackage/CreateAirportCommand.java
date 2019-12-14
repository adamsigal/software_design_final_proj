package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.PlaneManager;

public class CreateAirportCommand implements IAdminCommand {
    private String addedBuildingId;
    private String city;

    public CreateAirportCommand(String addedBuildingId, String city) {
        this.addedBuildingId = addedBuildingId;
        this.city = city;
    }

    @Override
    public void execute() {
        PlaneManager.getInstance().createBuilding(addedBuildingId, city);
    }

    @Override
    public void undo() {
        if(!addedBuildingId.equals("")){
            BuildingRegister.getInstance().delete((addedBuildingId));
            addedBuildingId = "";
            city="";
        }

    }
}