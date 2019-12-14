package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.TrainManager;

public class CreateTrainStationCommand implements IAdminCommand {
    private String addedBuildingId;
    private String city;

    public CreateTrainStationCommand(String addedBuildingId, String city) {
        this.addedBuildingId = addedBuildingId;
        this.city = city;
    }

    @Override
    public void execute() {
        TrainManager.getInstance().createBuilding(addedBuildingId,city);
    }

    @Override
    public void undo() {
        if (!addedBuildingId.equals("")) {
            BuildingRegister.getInstance().delete((addedBuildingId));
            addedBuildingId = "";
            city="";
        }
    }
}