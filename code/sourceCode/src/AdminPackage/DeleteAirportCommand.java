package AdminPackage;

import ModelPackages.BuildingRegister;
import TravelPackage.PlaneManager;

public class DeleteAirportCommand implements IAdminCommand {
    private String deletedBuildingId;
    private String deleteBuildingCity;

    public DeleteAirportCommand(String deletedBuildingId, String deleteBuildingCity) {
        this.deletedBuildingId = deletedBuildingId;
        this.deleteBuildingCity = deleteBuildingCity;
    }

    @Override
    public void execute() {
        BuildingRegister.getInstance().delete(deletedBuildingId);
    }

    @Override
    public void undo() {
        if(!deletedBuildingId.equals("")){

            PlaneManager.getInstance().createBuilding(deletedBuildingId,deleteBuildingCity);

            deleteBuildingCity="";
            deletedBuildingId="";
        }
    }
}