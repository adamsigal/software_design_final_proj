package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.CruiseManager;

public class DeletePortCommand implements IAdminCommand {
    private String deletedBuildingId;
    private String deleteBuildingCity;

    public DeletePortCommand(String deletedBuildingId, String deleteBuildingCity) {
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

            CruiseManager.getInstance().createBuilding(deletedBuildingId,deleteBuildingCity);

            deleteBuildingCity = "";
            deletedBuildingId = "";
        }
    }
}