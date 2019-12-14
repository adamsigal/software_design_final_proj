package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.CruiseManager;

public class CreatePortCommand implements IAdminCommand {
    private String addedBuildingId;
    private String city;

    public CreatePortCommand(String addedBuildingId, String city) {
        this.addedBuildingId = addedBuildingId;
        this.city = city;
    }

    @Override
    public void execute() {

       CruiseManager.getInstance().createBuilding(addedBuildingId, city);
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