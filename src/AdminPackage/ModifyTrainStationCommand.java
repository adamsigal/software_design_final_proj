package AdminPackage;

import ModelPackages.TrainStation;

public class ModifyTrainStationCommand implements IAdminCommand {
    String newCity;
    String newId;
    TrainStation trainStation;
    String oldCity;
    String oldId;

    public ModifyTrainStationCommand(String newCity, String newId, TrainStation trainStation) {
        this.newCity = newCity;
        this.newId = newId;
        this.trainStation = trainStation;
        this.oldCity = trainStation.getCity();
        this.oldId = trainStation.getBuildingId();
    }

    @Override
    public void execute() {
        if(!newCity.equals("")){
            trainStation.setCity(newCity);
        }
        if(!newId.equals("")){
            trainStation.setBuildingId(newId);
        }
    }

    @Override
    public void undo() {
        trainStation.setBuildingId(oldId);
        trainStation.setCity(oldCity);
    }
}