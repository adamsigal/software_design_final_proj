package UI;

import AdminPackage.*;
import ModelPackages.BuildingRegister;
import ModelPackages.Airport;
import ModelPackages.IBuilding;
import ModelPackages.Port;
import ModelPackages.TrainStation;
import ObserverPackage.Observer;
import TravelPackage.*;

import java.util.ArrayList;
import java.util.List;


public class AdminITravelController extends Controller implements Observer {
    private ArrayList<ITravel> register;
    private static UI.AdminITravelController instance;

    public static UI.AdminITravelController getInstance() {
        return instance;
    }

    public AdminITravelController() {
        instance = this;
    }

    /**
     * @param id the id of the building you want to obtain
     * @return the IBuilding object that corresponds to the given id
     */
    private IBuilding getBuilding(String id) {
        return BuildingRegister.getInstance().get(id);
    }

    /**
     * @param arrivalDate   the date of arrival of the flight
     * @param arrivalTime   the arrival time of the flight
     * @param departureDate the departure date of the flight
     * @param departureTime the departure date of the flight
     * @param sourceId      the id of the airport of departure
     * @param destinationId the id of the airport of arrival
     * @param travelId      the id of the flight to create
     */
    public void createFlight(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String travelId,String companyId) {
        Airport source = (Airport) getBuilding(sourceId);
        Airport destination = (Airport) getBuilding(destinationId);
        TravelId travelId1 = new TravelId(travelId);
        IAdminCommand command = new CreateFlightCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelId1, companyId);
        commandManager.executeCommand(command);
    }

    /**
     * @param arrivalDate    the date of arrival of the cruise
     * @param arrivalTime    the arrival time of the cruise
     * @param departureDate  the departure date of the cruise
     * @param departureTime  the departure date of the cruise
     * @param sourceId       the id of the port of departure
     * @param destinationId  the id of the port of arrival
     * @param sequenceString an ArrayList containing the ids of each intermediary port on the itinerary
     */
    public void createCruise(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String travelId, List<String> sequenceString,String companyId) {
        Port source = (Port) getBuilding(sourceId);
        Port destination = (Port) getBuilding(destinationId);
        TravelId travelId1 = new TravelId(travelId);
        //convert the ArrayList of String in an ArrayList of port
        ArrayList<Port> portSequence = new ArrayList<>();
        for (String id : sequenceString) {
            portSequence.add((Port) getBuilding(id));
        }
        IAdminCommand command = new CreateCruiseCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelId1, portSequence, companyId);
        commandManager.executeCommand(command);
    }

    /**
     * @param arrivalDate    the date of arrival of the train traject
     * @param arrivalTime    the arrival time of the train traject
     * @param departureDate  the departure date of the train traject
     * @param departureTime  the departure date of the train traject
     * @param sourceId       the id of the train station of departure
     * @param destinationId  the id of the train station of arrival
     * @param sequenceString an ArrayList containing the ids of each intermediary train station on the itinerary
     */
    public void createTrainTraject(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String travelId, List<String> sequenceString,String companyId) {
        TrainStation source = (TrainStation) getBuilding(sourceId);
        TrainStation destination = (TrainStation) getBuilding(destinationId);
        TravelId travelId1 = new TravelId(travelId);
        //convert the ArrayList of String in an ArrayList of train station
        ArrayList<TrainStation> stationSequence = new ArrayList<>();
        for (String id : sequenceString) {
            stationSequence.add((TrainStation) getBuilding(id));
        }
        IAdminCommand command = new CreateTrainTrajectCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelId1, stationSequence, companyId);
        commandManager.executeCommand(command);
    }

    /**
     * @param arrivalDate    the date of arrival of the cruise
     * @param arrivalTime    the arrival time of the cruise
     * @param departureDate  the departure date of the cruise
     * @param departureTime  the departure date of the cruise
     * @param sourceId       the id of the port of departure
     * @param destinationId  the id of the port of arrival
     * @param oldTravelId    the current id of the cruise
     * @param newTravelId    the new id of the cruise
     * @param sequenceString an ArrayList containing the ids of each intermediary port on the itinerary
     */
    public void modifyCruiseById(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String oldTravelId, String newTravelId, List<String> sequenceString) {

        TravelId travelId1 = new TravelId(oldTravelId);
        Cruise cruise = (Cruise) TravelRegister.getInstance().get(travelId1);
        Port source = (Port) getBuilding(sourceId);
        Port destination = (Port) getBuilding(destinationId);
        //convert the ArrayList of String in an ArrayList of port
        ArrayList<Port> portSequence = new ArrayList<>();
        for (String id : sequenceString) {
            portSequence.add((Port) getBuilding(id));
        }
        TravelId travelIdNew = new TravelId(newTravelId);
        IAdminCommand command = new ModifyCruiseCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelIdNew, portSequence, cruise);
        commandManager.executeCommand(command);
    }

    /**
     * @param arrivalDate    the date of arrival of the train traject
     * @param arrivalTime    the arrival time of the train traject
     * @param departureDate  the departure date of the train traject
     * @param departureTime  the departure date of the train traject
     * @param sourceId       the id of the train station of departure
     * @param destinationId  the id of the train station of arrival
     * @param oldTravelId    the current id of the train traject
     * @param newTravelId    the new id of the traject
     * @param sequenceString an ArrayList containing the ids of each intermediary train station on the itinerary
     */
    public void modifyTrainTrajectById(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String oldTravelId, String newTravelId, List<String> sequenceString) {

        TravelId travelId1 = new TravelId(oldTravelId);
        TrainTraject trainTraject = (TrainTraject) TravelRegister.getInstance().get(travelId1);
        TrainStation source = (TrainStation) getBuilding(sourceId);
        TrainStation destination = (TrainStation) getBuilding(destinationId);
        //convert the ArrayList of String in an Arraylist of train station
        ArrayList<TrainStation> trainStations = new ArrayList<>();
        for (String id : sequenceString) {
            trainStations.add((TrainStation) getBuilding(id));
        }
        TravelId travelIdNew = new TravelId(newTravelId);
        IAdminCommand command = new ModifyTrainTrajectCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelIdNew, trainStations, trainTraject);
        commandManager.executeCommand(command);
    }

    /**
     * @param arrivalDate   the date of arrival of the flight
     * @param arrivalTime   the arrival time of the flight
     * @param departureDate the departure date of the flight
     * @param departureTime the departure date of the flight
     * @param sourceId      the id of the airport of departure
     * @param destinationId the id of the airport of arrival
     * @param oldTravelId   the current id of the flight
     * @param newTravelId   the new id of the flight
     */
    public void modifyFlightById(String arrivalDate, String arrivalTime, String departureDate, String departureTime, String sourceId, String destinationId, String oldTravelId, String newTravelId) {
        TravelId travelIdOld = new TravelId(oldTravelId);
        Flight flight = (Flight) TravelRegister.getInstance().get(travelIdOld);
        Airport source = (Airport) getBuilding(sourceId);
        Airport destination = (Airport) getBuilding(destinationId);
        TravelId travelIdNew = new TravelId(newTravelId);
        IAdminCommand command = new ModifyFlightCommand(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelIdNew, flight);
        commandManager.executeCommand(command);
    }

    /**
     * @param newPrice the new price of the specified train traject
     * @param idTravel the id of the train traject to wich you want to assign a new price
     */
    public void assignTrainTrajectPrice(float newPrice, String idTravel) {
        TrainLineId travelId = new TrainLineId(idTravel);
        IAdminCommand command = new AssignTrainTrajectPriceCommand(newPrice, travelId);
        commandManager.executeCommand(command);

    }

    /**
     * @param newPrice the new price of the specified cruise
     * @param idTravel the id of the cruise to wich you want to assign a new price
     */
    public void assignCruisePrice(float newPrice, String idTravel) {
        CruiseId travelId = new CruiseId(idTravel);
        IAdminCommand command = new AssignCruisePriceCommand(travelId, newPrice);
        commandManager.executeCommand(command);

    }

    /**
     * @param newPrice the new price of the specified flight
     * @param idTravel the id of the flight to wich you want to assign a new price
     */
    public void assignFlightPrice(float newPrice, int intId,String idTravel) {
        FlightId travelId = new FlightId(intId,idTravel);
        IAdminCommand command = new AssignFlightPriceCommand(travelId, newPrice);
        commandManager.executeCommand(command);

    }


    /**
     * @param id the id of the train traject you wish to delete
     */
    public void deleteTrainTrajectById(String id) {
        TrainTraject trainTraject = (TrainTraject) TravelRegister.getInstance().get(new TravelId(id));
        IAdminCommand command = new DeleteTrainTrajectCommand(trainTraject);
        commandManager.executeCommand(command);
    }

    /**
     * @param id the id of the cruise you wish to delete
     */
    public void deleteCruiseById(String id) {
        Cruise cruise = (Cruise) TravelRegister.getInstance().get(new TravelId(id));
        IAdminCommand command = new DeleteCruiseCommand(cruise);
        commandManager.executeCommand(command);
    }

    /**
     * @param id the id of the flight you wish to delete
     */
    public void deleteFlightById(String id) {
        Flight flight = (Flight) TravelRegister.getInstance().get(new TravelId(id));
        IAdminCommand command = new DeleteFlightCommand(flight);
        commandManager.executeCommand(command);
    }

    @Override
    public <T> void updateRegister(ArrayList<T> register) {
        this.register = (ArrayList<ITravel>) register;
    }
}
