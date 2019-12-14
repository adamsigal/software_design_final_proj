package AdminPackage;

import ModelPackages.CompanyRegister;
import ModelPackages.ICompany;
import ModelPackages.Port;
import ModelPackages.TrainStation;
import TravelPackage.TrainManager;
import TravelPackage.TravelId;
import TravelPackage.TravelRegister;

import java.util.List;

public class CreateTrainTrajectCommand implements IAdminCommand {
    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
    private TrainStation source;
    private TrainStation destination;
    private TravelId travelId;
    private List<TrainStation> sequence;
    private ICompany company;

    public CreateTrainTrajectCommand(String arrivalDate, String arrivalTime, String departureDate, String departureTime, TrainStation source, TrainStation destination, TravelId travelId, List<TrainStation> sequence, String companyId) {
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
        TravelRegister.getInstance().add(TrainManager.getInstance().createTraject(arrivalDate, arrivalTime, departureDate, departureTime, source, destination, travelId, sequence, company));

    }

    @Override
    public void undo() {
        TravelRegister.getInstance().delete(travelId);
    }
}