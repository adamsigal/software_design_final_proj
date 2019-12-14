package TravelPackage;

import ModelPackages.BuildingRegister;
import ModelPackages.CompanyRegister;
import ReservablePackage.*;
import SectionPackage.EconomicTrainSection;
import SectionPackage.FirstTrainSection;
import SectionPackage.ITrainSection;
import ModelPackages.*;
import VehiculePackage.IVehicule;
import VehiculePackage.Train;
import VehiculePackage.VehiculeRegister;

import java.util.ArrayList;

public class TrainManager implements CompanyCreator, Buildable {

    private static TrainManager instance = new TrainManager();

    private static int MAX_ROW = 100;
    private static int MAX_COLUMNS = 10;
    private static char[] seatChar = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J'};

    private TrainManager() {

    }

    public static TrainManager getInstance() {
        return TrainManager.instance;
    }

    /**
     * @param arrivalDate
     * @param arrivalTime
     * @param departureDate
     * @param departureTime
     * @param source
     * @param destination
     * @param travelId
     * @param trainLineSequence
     */
    public ITravel createTraject(String arrivalDate, String arrivalTime, String departureDate, String departureTime, IBuilding source, IBuilding destination, TravelId travelId, java.util.List<TrainStation> trainLineSequence,ICompany company) {
        TrainTraject trainTraject = new TrainTraject();
        trainTraject.setArrivalDate(arrivalDate);
        trainTraject.setArrivalTime(arrivalTime);
        trainTraject.setDepartureDate(departureDate);
        trainTraject.setDepartureTime(departureTime);
        trainTraject.setSource(source);
        trainTraject.setDestination(destination);
        trainTraject.setTravelId(travelId);
        trainTraject.setSequence(trainLineSequence);
        trainTraject.setCompany(company);
        TravelRegister.getInstance().add(trainTraject);
        return trainTraject;
    }


    @Override
    public IBuilding createBuilding(String buildingID, String city) {
        TrainStation trainStation = new TrainStation(buildingID, city);
        BuildingRegister.getInstance().add(trainStation);
        return trainStation;
    }

    @Override
    public ICompany createCompany(String companyId) {
        TrainLineCompany trainLineCompany = new TrainLineCompany(companyId);
        CompanyRegister.getInstance().add(trainLineCompany);
        return trainLineCompany;
    }

    public IVehicule createVehicule() {

        ArrayList<ITrainSection> sectionList = new ArrayList<>();
        sectionList.add(new FirstTrainSection());
        sectionList.add(new EconomicTrainSection());

        for (ITrainSection section : sectionList) {
            populateReservable(section);
        }
        Train newTrain = new Train(sectionList);
        VehiculeRegister.getInstance().addVehicule(newTrain);
        return newTrain;
    }

    private void populateReservable(ITrainSection section) {
        int rowToGenerate = MAX_ROW / 2;
        int seatToGenerate = rowToGenerate * 3;
        //always 2 sections;

        ArrayList<TrainSeatable> seatList = new ArrayList<>();

        for (int i = 0; i < seatToGenerate; i++) {
            TrainSeat trainSeat = null;
            SmallSeats smallSeats = new SmallSeats();
            trainSeat = new TrainSeat(smallSeats);
            trainSeat.setStateIReservable(new AvailableState());
            trainSeat.setId(new SeatId(i % rowToGenerate, seatChar[i % smallSeats.getColumnCount()]));
            if (trainSeat.getId().getColumn() == 'A') {
                trainSeat.setOnAisle(true);
                trainSeat.setOnWindow(true);
            } else if (trainSeat.getId().getColumn() == 'B')
                trainSeat.setOnAisle(true);
            else if (trainSeat.getId().getColumn() == 'C')
                trainSeat.setOnWindow(true);

            seatList.add(trainSeat);
        }
        section.setReservableList(seatList);

    }
}