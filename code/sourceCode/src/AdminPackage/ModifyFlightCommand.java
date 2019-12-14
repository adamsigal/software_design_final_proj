package AdminPackage;

import ModelPackages.Airport;
import ModelPackages.IBuilding;
import ModelPackages.Port;
import TravelPackage.Flight;
import TravelPackage.TravelId;

public class ModifyFlightCommand implements IAdminCommand {
    private String newArrivalDate;
    private String newArrivalTime;
    private String newDepartureDate;
    private String newDepartureTime;
    private Airport newSource;
    private Airport newDestination;
    private TravelId newTravelId;
    private Flight flight;

    private String oldArrivalDate;
    private String oldArrivalTime;
    private String oldDepartureDate;
    private String oldDepartureTime;
    private IBuilding oldSource;
    private IBuilding oldDestination;
    private TravelId oldTravelId;

    public ModifyFlightCommand(String newArrivalDate, String newArrivalTime, String newDepartureDate, String newDepartureTime, Airport newSource, Airport newDestination, TravelId newTravelId, Flight flight) {
        this.newArrivalDate = newArrivalDate;
        this.newArrivalTime = newArrivalTime;
        this.newDepartureDate = newDepartureDate;
        this.newDepartureTime = newDepartureTime;
        this.newSource = newSource;
        this.newDestination = newDestination;
        this.newTravelId = newTravelId;
        this.flight = flight;

        this.oldArrivalDate = flight.getArrivalDate();
        this.oldArrivalTime = flight.getArrivalTime();
        this.oldDepartureDate = flight.getDepartureDate();
        this.oldDepartureTime = flight.getDepartureTime();
        this.oldSource = flight.getSource();
        this.oldDestination = flight.getDestination();
        this.oldTravelId = flight.getTravelId();
    }

    @Override
    public void execute() {
        if(!newArrivalDate.equals("")){
            flight.setArrivalDate(newArrivalDate);
        }
        if(!newArrivalTime.equals("")){
            flight.setArrivalTime(newArrivalTime);
        }
        if(!newDepartureDate.equals("")){
            flight.setDepartureDate(newDepartureDate);
        }
        if(!newDepartureTime.equals("")){
            flight.setDepartureTime(newDepartureTime);
        }
        if(newSource != null){
            flight.setSource(newSource);
        }
        if(newDestination != null){
            flight.setDestination(newDestination);
        }
        if(newTravelId != null){
            flight.setTravelId(newTravelId);
        }
    }

    @Override
    public void undo() {
        flight.setArrivalDate(oldArrivalDate);
        flight.setArrivalTime(oldArrivalTime);
        flight.setDepartureDate(oldDepartureDate);
        flight.setDepartureTime(oldDepartureTime);
        flight.setSource(oldSource);
        flight.setDestination(oldDestination);
        flight.setTravelId(oldTravelId);
    }
}