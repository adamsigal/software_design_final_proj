package AdminPackage;

import AdminPackage.IAdminCommand.*;
import ModelPackages.IBuilding;
import ModelPackages.Port;
import TravelPackage.Cruise;
import TravelPackage.TravelId;

import java.util.List;

public class ModifyCruiseCommand implements  IAdminCommand {
    private String newArrivalDate;
    private String newArrivalTime;
    private String newDepartureDate;
    private String newDepartureTime;
    private Port newSource;
    private Port newDestination;
    private TravelId newTravelId;
    private List<Port> newSequence;

    private String oldArrivalDate;
    private String oldArrivalTime;
    private String oldDepartureDate;
    private String oldDepartureTime;
    private IBuilding oldSource;
    private IBuilding oldDestination;
    private TravelId oldTravelId;
    private List<Port> oldSequence;

    private Cruise cruise;

    public ModifyCruiseCommand(String newArrivalDate, String newArrivalTime, String newDepartureDate, String newDepartureTime, Port newSource, Port newDestination, TravelId newTravelId, List<Port> newSequence, Cruise cruise) {
        this.newArrivalDate = newArrivalDate;
        this.newArrivalTime = newArrivalTime;
        this.newDepartureDate = newDepartureDate;
        this.newDepartureTime = newDepartureTime;
        this.newSource = newSource;
        this.newDestination = newDestination;
        this.newTravelId = newTravelId;
        this.newSequence = newSequence;
        this.cruise = cruise;

        this.oldArrivalDate = cruise.getArrivalDate();
        this.oldArrivalTime = cruise.getArrivalTime();
        this.oldDepartureDate = cruise.getDepartureDate();
        this.oldDepartureTime = cruise.getDepartureTime();
        this.oldSource = cruise.getSource();
        this.oldDestination = cruise.getDestination();
        this.oldSequence = cruise.getSequence();
        this.oldTravelId = cruise.getTravelId();


    }

    @Override
    public void execute() {
        if(!newArrivalDate.equals("")){
            cruise.setArrivalDate(newArrivalDate);
        }
        if(!newArrivalTime.equals("")){
            cruise.setArrivalTime(newArrivalTime);
        }
        if(!newDepartureDate.equals("")){
            cruise.setDepartureDate(newDepartureDate);
        }
        if(!newDepartureTime.equals("")){
            cruise.setDepartureTime(newDepartureTime);
        }
        if(newSource != null){
            cruise.setSource(newSource);
        }
        if(newDestination != null){
            cruise.setDestination(newDestination);
        }
        if(newTravelId != null){
            cruise.setTravelId(newTravelId);
        }
        if(newSequence != null){
            cruise.setSequence(newSequence);
        }

    }

    @Override
    public void undo() {
        cruise.setArrivalDate(oldArrivalDate);
        cruise.setArrivalTime(oldArrivalTime);
        cruise.setDepartureDate(oldDepartureDate);
        cruise.setDepartureTime(oldDepartureTime);
        cruise.setSource(oldSource);
        cruise.setDestination(oldDestination);
        cruise.setTravelId(oldTravelId);
        cruise.setSequence(oldSequence);
    }
}