package AdminPackage;

import ModelPackages.IBuilding;
import ModelPackages.Port;
import ModelPackages.TrainStation;
import TravelPackage.TrainTraject;
import TravelPackage.TravelId;
import VehiculePackage.Train;

import java.util.List;

public class ModifyTrainTrajectCommand implements IAdminCommand {
    private String newArrivalDate;
    private String newArrivalTime;
    private String newDepartureDate;
    private String newDepartureTime;
    private TrainStation newSource;
    private TrainStation newDestination;
    private TravelId newTravelId;
    private List<TrainStation> newSequence;

    private String oldArrivalDate;
    private String oldArrivalTime;
    private String oldDepartureDate;
    private String oldDepartureTime;
    private IBuilding oldSource;
    private IBuilding oldDestination;
    private TravelId oldTravelId;
    private List<TrainStation> oldSequence;

    private TrainTraject trainTraject;

    public ModifyTrainTrajectCommand(String newArrivalDate, String newArrivalTime, String newDepartureDate, String newDepartureTime, TrainStation newSource, TrainStation newDestination, TravelId newTravelId, List<TrainStation> newSequence, TrainTraject trainTraject) {
        this.newArrivalDate = newArrivalDate;
        this.newArrivalTime = newArrivalTime;
        this.newDepartureDate = newDepartureDate;
        this.newDepartureTime = newDepartureTime;
        this.newSource = newSource;
        this.newDestination = newDestination;
        this.newTravelId = newTravelId;
        this.newSequence = newSequence;
        this.trainTraject = trainTraject;

        this.oldArrivalDate = trainTraject.getArrivalDate();
        this.oldArrivalTime = trainTraject.getArrivalTime();
        this.oldDepartureDate = trainTraject.getDepartureDate();
        this.oldDepartureTime = trainTraject.getDepartureTime();
        this.oldSource = trainTraject.getSource();
        this.oldDestination = trainTraject.getDestination();
        this.oldSequence = trainTraject.getSequence();
        this.oldTravelId = trainTraject.getTravelId();
    }

    @Override
    public void execute() {
        if(!newArrivalDate.equals("")){
            trainTraject.setArrivalDate(newArrivalDate);
        }
        if(!newArrivalTime.equals("")){
            trainTraject.setArrivalTime(newArrivalTime);
        }
        if(!newDepartureDate.equals("")){
            trainTraject.setDepartureDate(newDepartureDate);
        }
        if(!newDepartureTime.equals("")){
            trainTraject.setDepartureTime(newDepartureTime);
        }
        if(newSource != null){
            trainTraject.setSource(newSource);
        }
        if(newDestination != null){
            trainTraject.setDestination(newDestination);
        }
        if(newTravelId != null){
            trainTraject.setTravelId(newTravelId);
        }
        if(newSequence != null){
            trainTraject.setSequence(newSequence);
        }
    }

    @Override
    public void undo() {
        trainTraject.setArrivalDate(oldArrivalDate);
        trainTraject.setArrivalTime(oldArrivalTime);
        trainTraject.setDepartureDate(oldDepartureDate);
        trainTraject.setDepartureTime(oldDepartureTime);
        trainTraject.setSource(oldSource);
        trainTraject.setDestination(oldDestination);
        trainTraject.setTravelId(oldTravelId);
        trainTraject.setSequence(oldSequence);
    }
}