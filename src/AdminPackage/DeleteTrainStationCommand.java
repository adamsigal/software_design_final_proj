package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.TrainManager;

public class DeleteTrainStationCommand implements IAdminCommand {
    private String deletedBuildingId;
    private String deleteBuildingCity;

    public DeleteTrainStationCommand(String deletedBuildingId, String deleteBuildingCity) {
        this.deletedBuildingId = deletedBuildingId;
        this.deleteBuildingCity = deleteBuildingCity;
    }

    @Override
    public void execute() {
        BuildingRegister.getInstance().delete(deletedBuildingId);
    }

    @Override
    public void undo() {
        if (!deletedBuildingId.equals("")) {
            /*var newBuilding = new TrainStation();
            newBuilding.setBuildingId(deletedBuildingId);
            newBuilding.setCity(deleteBuildingCity);
            BuildingRegister.add(newBuilding);*/
            TrainManager.getInstance().createBuilding(deletedBuildingId,deleteBuildingCity);
            deleteBuildingCity = "";
            deletedBuildingId = "";
        }
    }

}