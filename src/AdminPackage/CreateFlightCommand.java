package AdminPackage;

import ModelPackages.Airport;
import ModelPackages.CompanyRegister;
import ModelPackages.ICompany;
import TravelPackage.PlaneManager;
import TravelPackage.TravelId;
import TravelPackage.TravelRegister;

public class CreateFlightCommand implements IAdminCommand {
    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
    private Airport source;
    private Airport destination;
    private TravelId travelId;
    private ICompany company;

    public CreateFlightCommand(String arrivalDate, String arrivalTime, String departureDate, String departureTime, Airport source, Airport destination, TravelId travelId, String companyId) {
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.source = source;
        this.destination = destination;
        this.travelId = travelId;
        this.company = CompanyRegister.getInstance().get(companyId);
    }

    @Override
    public void execute() {
        TravelRegister.getInstance().add(PlaneManager.getInstance().createFlight(arrivalDate, arrivalTime, departureDate, departureTime, source,destination, travelId, company));
    }

    @Override
    public void undo() {
        TravelRegister.getInstance().delete(travelId);
    }
}