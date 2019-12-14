package AdminPackage;

import ModelPackages.CompanyRegister;
import ModelPackages.ICompany;
import ModelPackages.Port;
import TravelPackage.CruiseManager;
import TravelPackage.TravelId;
import TravelPackage.TravelRegister;

import java.util.List;

public class CreateCruiseCommand implements IAdminCommand {
    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
    private Port source;
    private Port destination;
    private TravelId travelId;
    private List<Port> sequence;
    private ICompany company;

    public CreateCruiseCommand(String arrivalDate, String arrivalTime, String departureDate, String departureTime, Port source, Port destination, TravelId travelId, List<Port> sequence, String companyId) {
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.source = source;
        this.destination = destination;
        this.travelId = travelId;
        this.sequence = sequence;
        this.company = CompanyRegister.getInstance().get(companyId);
    }

    @Override
    public void execute() {
        TravelRegister.getInstance().add(CruiseManager.getInstance().createTraject(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelId, sequence,company));

    }

    @Override
    public void undo() {
        TravelRegister.getInstance().delete(travelId);
    }
}