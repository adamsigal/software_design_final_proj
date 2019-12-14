package UI;

import AdminPackage.*;
import ModelPackages.CompanyRegister;
import ModelPackages.AirlineCompany;
import ModelPackages.CruiseCompany;
import ModelPackages.ICompany;
import ModelPackages.TrainLineCompany;
import ObserverPackage.Observer;
import TravelPackage.CruiseManager;
import TravelPackage.PlaneManager;
import TravelPackage.TrainManager;


import java.util.ArrayList;

public class AdminCompanyController extends Controller implements Observer {
    ArrayList<ICompany> register;
    private static AdminCompanyController instance;

    public static AdminCompanyController getInstance() {
        return instance;
    }

    public AdminCompanyController() {
        instance = this;
    }

    /**
     * @param newId the new id of the airline company
     * @param oldId the current id of the airline company
     */
    public void modifyAirlineCompanyById(String newId, String oldId) {
        AirlineCompany airlineCompany = (AirlineCompany) CompanyRegister.getInstance().get(oldId);
        IAdminCommand command = new ModifyAirLineCompanyCommand(newId, airlineCompany);
        commandManager.executeCommand(command);
    }

    /**
     * @param newId the new id of the train line company
     * @param oldId the current id of the train line company
     */
    public void modifyTrainlineCompanyById(String newId, String oldId) {
        TrainLineCompany trainLineCompany = (TrainLineCompany) CompanyRegister.getInstance().get(oldId);
        IAdminCommand command = new ModifyTrainLineCompanyCommand(newId, trainLineCompany);
        commandManager.executeCommand(command);
    }

    /**
     * @param newId the new id of the cruise company
     * @param oldId the current id of the cruise company
     */
    public void modifyCruiseCompanyById(String newId, String oldId) {
        CruiseCompany cruiseCompany = (CruiseCompany) CompanyRegister.getInstance().get(oldId);
        IAdminCommand command = new ModifyCruiseCompanyCommand(newId, cruiseCompany);
        commandManager.executeCommand(command);
    }

    /**
     * @param id the  id of the train line company you want to create
     */
    public void createTrainLineCompany(String id) {
        TrainManager.getInstance().createCompany(id);
    }

    /**
     * @param id the id of the cruise company you want to create
     */
    public void createCruiseCompany(String id) {
        CruiseManager.getInstance().createCompany(id);
    }

    /**
     * @param id the id of the airline company you want to create
     */
    public void createAirlineCompany(String id) {
        PlaneManager.getInstance().createCompany(id);
    }

    /**
     * @param id the id of the cruise company you want to delete
     */
    public void deleteCruiseCompany(String id) {
        CompanyRegister.getInstance().delete(id);
    }

    /**
     * @param id the id of the airline company you want to delete
     */
    public void deleteAirlineCompany(String id) {
        CompanyRegister.getInstance().delete(id);
    }

    /**
     * @param id the id of the train line company you want to delete
     */
    public void deleteTrainlineCompany(String id) {
        CompanyRegister.getInstance().delete(id);
    }

    @Override
    public <T> void updateRegister(ArrayList<T> register) {
        this.register = (ArrayList<ICompany>) register;
    }
}
