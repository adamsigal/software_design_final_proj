package UI;

import AdminPackage.*;
import ModelPackages.BuildingRegister;
import ModelPackages.Airport;
import ModelPackages.IBuilding;
import ModelPackages.Port;
import ModelPackages.TrainStation;
import ObserverPackage.Observer;

import java.util.ArrayList;


public class AdminBuildingController extends Controller implements Observer {

    private ArrayList<IBuilding> register;
    private static AdminBuildingController instance;

    public static AdminBuildingController getInstance() {
        return instance;
    }

    public AdminBuildingController() {
        instance = this;
    }

    /**
     * @param id the id of the building wanted
     * @return the IBuilding object corresponding to the id
     */
    private IBuilding getBuilding(String id) {
        return BuildingRegister.getInstance().get(id);
    }

    /**
     * @param buildingId   the id of the train station you want to destroy
     * @param buildingCity the city in wich the train station you want to destroy is situated
     */
    public void deleteTrainStation(String buildingId, String buildingCity) {
        IAdminCommand command = new DeleteTrainStationCommand(buildingId, buildingCity);
        commandManager.executeCommand(command);
    }

    /**
     * @param buildingId   the id of the airport you want to destroy
     * @param buildingCity the city in wich the airport you want to destroy is situated
     */
    public void deleteAirport(String buildingId, String buildingCity) {
        IAdminCommand command = new DeleteAirportCommand(buildingId, buildingCity);
        commandManager.executeCommand(command);
    }

    /**
     * @param buildingId   the id of the port you want to destroy
     * @param buildingCity the city in wich the port you want to destroy is situated
     */
    public void deletePort(String buildingId, String buildingCity) {
        IAdminCommand command = new DeletePortCommand(buildingId, buildingCity);
        commandManager.executeCommand(command);
    }

    /**
     * @param newCity the new city in wich the airport is situated
     * @param newId   the id that you want to give to the airport
     * @param oldId   the id of the airport you want to modify
     */
    public void modifyAirportCommandById(String newCity, String newId, String oldId) {
        Airport airport = (Airport) getBuilding(oldId);
        IAdminCommand command = new ModifyAirPortCommand(newCity, newId, airport);
        commandManager.executeCommand(command);
    }

    /**
     * @param newCity the new city in wich the port is situated
     * @param newId   the id that you want to give to the port
     * @param oldId   the id of the port you want to modify
     */
    public void modifyPortCommandById(String newCity, String newId, String oldId) {
        Port port = (Port) getBuilding(oldId);
        IAdminCommand command = new ModifyPortCommand(newCity, newId, port);
        commandManager.executeCommand(command);
    }

    /**
     * @param newCity the new city in wich the train station is situated
     * @param newId   the id that you want to give to the train station
     * @param oldId   the id of the train station you want to modify
     */
    public void modifyTrainStationCommandById(String newCity, String newId, String oldId) {
        TrainStation trainStation = (TrainStation) getBuilding(oldId);
        IAdminCommand command = new ModifyTrainStationCommand(newCity, newId, trainStation);
        commandManager.executeCommand(command);
    }

    /**
     * @param buildingId the id of the port you want to create
     * @param city       the city in wich the port you want to create is situated
     */
    public void createPortCommand(String buildingId, String city) {
        IAdminCommand command = new CreatePortCommand(buildingId, city);
        commandManager.executeCommand(command);
    }

    /**
     * @param buildingId the id of the airport you want to create
     * @param city       the city in wich the airport you want to create is situated
     */
    public void createAirportCommand(String buildingId, String city) {
        IAdminCommand command = new CreateAirportCommand(buildingId, city);
        commandManager.executeCommand(command);
    }

    /**
     * @param buildingId the id of the train station you want to create
     * @param city       the city in wich the train station you want to create is situated
     */
    public void createTrainStationCommand(String buildingId, String city) {
        IAdminCommand command = new CreateTrainStationCommand(buildingId, city);
        commandManager.executeCommand(command);
    }

    /**
     * @param id the id of the building from wich you want to know the city
     * @return the city in wich the building corresponding to the id is situated
     */
    public String getCityOfBuilding(String id) {
        return getBuilding(id).getCity();
    }


    @Override
    public <T> void updateRegister(ArrayList<T> register) {
        this.register = (ArrayList<IBuilding>) register;
    }
}
