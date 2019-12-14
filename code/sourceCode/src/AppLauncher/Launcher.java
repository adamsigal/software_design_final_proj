package AppLauncher;

import ModelPackages.*;
import ClientPackage.Client;
import ClientPackage.ClientManager;
import ClientPackage.ClientRegister;
import ObserverPackage.ObserverManager;
import ReservablePackage.SeatType;
import SectionPackage.*;
import TravelPackage.*;
import UI.*;
import VehiculePackage.CruiseShip;
import VehiculePackage.Plane;
import VehiculePackage.Train;

import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {
    private static Scanner scanner = new Scanner(System.in);

    private static void display(String message) {
        System.out.println(message);
    }

    public static boolean isRunning = true;

    private static void initCruiseDataBase() {
        ICompany company1 = CruiseManager.getInstance().createCompany("CruONE");
        ICompany company2 = CruiseManager.getInstance().createCompany("CruTWO");
        ICompany company3 = CruiseManager.getInstance().createCompany("CruTHR");

        IBuilding building1 = CruiseManager.getInstance().createBuilding("Po1", "Montreal");
        IBuilding building2 = CruiseManager.getInstance().createBuilding("Po2", "New York");
        IBuilding building3 = CruiseManager.getInstance().createBuilding("Po3", "Vancouver");
        IBuilding building4 = CruiseManager.getInstance().createBuilding("Po4", "Quebec City");
        IBuilding building5 = CruiseManager.getInstance().createBuilding("Po5", "Iqaluit");
        IBuilding building6 = CruiseManager.getInstance().createBuilding("Po6", "Miami");

        ArrayList<Port> sequence1 = new ArrayList();
        sequence1.add((Port)building1);
        sequence1.add((Port) building2);
        sequence1.add((Port) building3);

        ArrayList<Port> sequence2 = new ArrayList();
        sequence1.add((Port) building4);
        sequence1.add((Port) building5);
        sequence1.add((Port) building6);


        ArrayList<Port> sequence3 = new ArrayList();
        sequence1.add((Port) BuildingRegister.getInstance().get("Po1"));
        sequence1.add((Port) BuildingRegister.getInstance().get("Po3"));
        sequence1.add((Port) BuildingRegister.getInstance().get("Po6"));

        Cruise cruise1 = (Cruise) CruiseManager.getInstance().createTraject("2019-01-31",
                "23:30", "2019-01-01", "0:00",
                BuildingRegister.getInstance().get("Po1"), BuildingRegister.getInstance().get("Po3"),
                new CruiseId("111ONE"), sequence1,company1);
        Cruise cruise2 = (Cruise) CruiseManager.getInstance().createTraject("2019-02-31",
                "23:30", "2019-02-01", "0:00",
                BuildingRegister.getInstance().get("Po4"), BuildingRegister.getInstance().get("Po6"),
                new CruiseId("222TWO"), sequence2,company2);
        Cruise cruise3 = (Cruise) CruiseManager.getInstance().createTraject("2019-03-31",
                "23:30", "2019-03-01", "0:00",
                BuildingRegister.getInstance().get("Po1"), BuildingRegister.getInstance().get("Po6"),
                new CruiseId("333THR"), sequence3,company3);

        CruiseShip boat1 = (CruiseShip) CruiseManager.getInstance().createVehicule();
        CruiseShip boat2 = (CruiseShip) CruiseManager.getInstance().createVehicule();

        cruise1.setBoat(boat1);
        cruise2.setBoat(boat2);
        cruise3.setBoat(boat1);

    }

    public static void initFlightDatabase() {
        ICompany company1 = PlaneManager.getInstance().createCompany("FliONE");
        ICompany company2 = PlaneManager.getInstance().createCompany("FliTWO");
        ICompany company3 = PlaneManager.getInstance().createCompany("FliTHR");

        PlaneManager.getInstance().createBuilding("AP1", "Montreal");
        PlaneManager.getInstance().createBuilding("AP2", "New York");
        PlaneManager.getInstance().createBuilding("AP3", "Vancouver");
        PlaneManager.getInstance().createBuilding("AP4", "Quebec City");
        PlaneManager.getInstance().createBuilding("AP5", "Iqaluit");
        PlaneManager.getInstance().createBuilding("AP6", "Miami");

        Flight flight1 = (Flight) PlaneManager.getInstance().createFlight("2019-05-03", "5:30",
                "2019-05-01", "2:30",
                BuildingRegister.getInstance().get("AP1"), BuildingRegister.getInstance().get("AP2"), new FlightId(111, "ONE"), company1);

        Flight flight2 = (Flight) PlaneManager.getInstance().createFlight("2019-06-03", "7:30",
                "2019-06-01", "8:30",
                BuildingRegister.getInstance().get("AP3"), BuildingRegister.getInstance().get("AP4"), new FlightId(222, "TWO"),company2);

        Flight flight3 = (Flight) PlaneManager.getInstance().createFlight("2019-07-03", "6:30",
                "2019-07-01", "3:45",
                BuildingRegister.getInstance().get("AP4"), BuildingRegister.getInstance().get("AP5"), new FlightId(333, "THR"),company2);

        ArrayList<IPlaneSection> section1 = new ArrayList<>();
        section1.add(new PremiumSection(SeatType.LargeSeat));
        section1.add(new BusinessSection(SeatType.MediumSeat));
        section1.add(new FirstPlaneSection(SeatType.ComfortSeat));
        section1.add(new EconomicPlaneSection(SeatType.SmallSeat));

        ArrayList<IPlaneSection> section2 = new ArrayList<>();
        section2.add(new PremiumSection(SeatType.MediumSeat));
        section2.add(new BusinessSection(SeatType.SmallSeat));
        section2.add(new FirstPlaneSection(SeatType.LargeSeat));
        section2.add(new EconomicPlaneSection(SeatType.ComfortSeat));

        ArrayList<IPlaneSection> section3 = new ArrayList<>();
        section3.add(new PremiumSection(SeatType.LargeSeat));
        section3.add(new BusinessSection(SeatType.SmallSeat));
        section3.add(new FirstPlaneSection(SeatType.ComfortSeat));
        section3.add(new EconomicPlaneSection(SeatType.MediumSeat));

        Plane plane1 = (Plane) PlaneManager.getInstance().createVehicule(section1);
        Plane plane2 = (Plane) PlaneManager.getInstance().createVehicule(section2);
        Plane plane3 = (Plane) PlaneManager.getInstance().createVehicule(section3);

        flight1.setPlane(plane1);
        flight2.setPlane(plane2);
        flight3.setPlane(plane3);

    }

    public static void initTrainDatabase() {
        ICompany company1 = TrainManager.getInstance().createCompany("TtcONE");
        ICompany company2 = TrainManager.getInstance().createCompany("TtcTWO");
        ICompany company3 = TrainManager.getInstance().createCompany("TtcTHR");

        TrainManager.getInstance().createBuilding("TS1", "Montreal");
        TrainManager.getInstance().createBuilding("TS2", "New York");
        TrainManager.getInstance().createBuilding("TS3", "Vancouver");
        TrainManager.getInstance().createBuilding("TS4", "Quebec City");
        TrainManager.getInstance().createBuilding("TS5", "Iqaluit");
        TrainManager.getInstance().createBuilding("TS6", "Miami");

        ArrayList<TrainStation> sequence1 = new ArrayList();
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS1"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS2"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS3"));

        ArrayList<TrainStation> sequence2 = new ArrayList();
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS4"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS5"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS6"));


        ArrayList<TrainStation> sequence3 = new ArrayList();
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS1"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS3"));
        sequence1.add((TrainStation) BuildingRegister.getInstance().get("TS6"));

        TrainTraject trainTraject1 = (TrainTraject) TrainManager.getInstance().createTraject("2019-02-31",
                "23:30", "2019-02-01", "0:00",
                BuildingRegister.getInstance().get("TS1"), BuildingRegister.getInstance().get("TS3"),
                new TrainLineId("111ONE"), sequence1, company1);
        TrainTraject trainTraject2 = (TrainTraject) TrainManager.getInstance().createTraject("2019-07-31",
                "23:30", "2019-07-01", "0:00",
                BuildingRegister.getInstance().get("TS4"), BuildingRegister.getInstance().get("TS6"),
                new TrainLineId("222TWO"), sequence2,company2);
        TrainTraject trainTraject3 = (TrainTraject) TrainManager.getInstance().createTraject("2019-10-31",
                "23:30", "2019-10-01", "0:00",
                BuildingRegister.getInstance().get("TS1"), BuildingRegister.getInstance().get("TS6"),
                new TrainLineId("333THR"), sequence3,company3);


        Train train1 = (Train) TrainManager.getInstance().createVehicule();
        Train train2 = (Train) TrainManager.getInstance().createVehicule();

        trainTraject1.setTrain(train1);
        trainTraject2.setTrain(train2);
        trainTraject3.setTrain(train1);
    }

    private static void initClientDatabase() {
        Client client1 = ClientManager.getInstance().createClient("Monsieur Untel", "2020 du Finfin", "test1@test.com", "555-555-5555", "2019-01-01", 123456, "2019-12-12");
        Client client2 = ClientManager.getInstance().createClient("Madame Unetelle", "2020 du Finfin", "test2@test.com", "555-555-5555", "2019-01-01", 17891011, "2019-12-12");
        Client client3 = ClientManager.getInstance().createClient("Jo Blo", "2020 du Finfin", "tes3@test.com", "555-555-5555", "2019-01-01", 123456, "2019-12-12");
    }

    public static void initDataBase() {
        initCruiseDataBase();
        initFlightDatabase();
        initTrainDatabase();
        initClientDatabase();
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        display("Welcome to our travel reservation application!");
        display("- Type admin if you are an admin");
        display("- Type your clientID to access the client interface");
        display("- Type exit to close the program");
        //TODO : add option to create a new account
        String response;


        ObserverManager.getInstance().attach(TravelRegister.getInstance(), AdminITravelController.getInstance());
        ObserverManager.getInstance().attach(BuildingRegister.getInstance(), AdminBuildingController.getInstance());
        ObserverManager.getInstance().attach(CompanyRegister.getInstance(), AdminCompanyController.getInstance());
        ObserverManager.getInstance().attach(TravelRegister.getInstance(), ClientController.getInstance());
        //VehiculeRegister is not observed yet
        //ClientRegister is not observed yet

        initDataBase();
        while (isRunning) {

            response = scanner.nextLine();

            switch (response) {
                case "admin":
                    AdminCommandLineInterface adminUi = new AdminCommandLineInterface();
                    adminUi.run();
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    if (isInteger(response)) {
                        int id = Integer.parseInt(response);
                        Client client = ClientRegister.getInstance().getClient(id);
                        if (client != null) {
                            ClientCommandLineInterface clientUi = new ClientCommandLineInterface();
                            display("Hi " + client.getName());
                            ClientController.getInstance().setCurrClient(id);
                            clientUi.run();
                        } else {
                            display("Unknown Client id, please try again");
                        }
                    } else {
                        display("Unknown command, please try again");
                    }
            }

        }
        display("Please come again!");

    }
}
