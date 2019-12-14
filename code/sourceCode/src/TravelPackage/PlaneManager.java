package TravelPackage;

import ModelPackages.BuildingRegister;
import ModelPackages.CompanyRegister;
import ModelPackages.AirlineCompany;
import ModelPackages.Airport;
import ModelPackages.IBuilding;
import ModelPackages.ICompany;
import ReservablePackage.*;
import SectionPackage.IPlaneSection;
import VehiculePackage.IVehicule;
import VehiculePackage.Plane;
import VehiculePackage.VehiculeRegister;

import java.util.ArrayList;

public class PlaneManager implements Buildable, CompanyCreator {

	private static PlaneManager instance =new PlaneManager();

	public static PlaneManager getInstance() {
		return instance;
	}

	private static int MAX_ROW = 100;
	private static int MAX_COLUMNS = 10;

	private static char[] seatChar  = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J'};
	private PlaneManager() {

	}

	/**
	 *
	 * @param arrivalDate
	 * @param arrivalTime
	 * @param departureDate
	 * @param departureTime
	 * @param source
	 * @param destination
	 * @param travelId
	 */
	public ITravel createFlight(String arrivalDate, String arrivalTime, String departureDate, String departureTime, IBuilding source, IBuilding destination, TravelId travelId,ICompany company) {
		Flight flight = new Flight();
		flight.setArrivalDate(arrivalDate);
		flight.setArrivalTime(arrivalTime);
		flight.setDepartureDate(departureDate);
		flight.setDepartureTime(departureTime);
		flight.setSource(source);
		flight.setDestination(destination);
		flight.setTravelId(travelId);
		flight.setCompany(company);
		TravelRegister.getInstance().add(flight);
		return flight;
	}

	@Override
	public IBuilding createBuilding(String buildingID, String city) {
		Airport airport = new Airport(buildingID,city);
		BuildingRegister.getInstance().add(airport);
		return airport;
	}

	@Override
	public ICompany createCompany(String companyId) {
		AirlineCompany airlineCompany = new AirlineCompany(companyId);
		CompanyRegister.getInstance().add(airlineCompany);
		return airlineCompany;
	}

	public IVehicule createVehicule(ArrayList<IPlaneSection> sectionList) {
		int numberOfSections = sectionList.size();

		for(IPlaneSection section : sectionList){
			populateReservable(section, numberOfSections);
		}


		Plane newPlane = new Plane(sectionList);
		VehiculeRegister.getInstance().addVehicule(newPlane);
		return newPlane;
	}

	private void populateReservable(IPlaneSection section, int numberOfSection){
		SeatType seatType = section.getSeatType();

		int rowToGenerate = MAX_ROW /numberOfSection;

		int seatToGenerate=0;

		if(seatType == SeatType.SmallSeat)
			seatToGenerate = rowToGenerate*3;
		else if(seatType == SeatType.ComfortSeat)
			seatToGenerate = rowToGenerate*4;
		else if(seatType == SeatType.MediumSeat)
			seatToGenerate = rowToGenerate*6;
		else if(seatType == SeatType.LargeSeat)
			seatToGenerate = rowToGenerate*10;

		ArrayList<PlaneSeatable> seatList = new ArrayList<>();
		
		for(int i = 0;i<seatToGenerate;i++){
			PlaneSeat planeSeat = null;
			if(seatType == SeatType.SmallSeat){
				SmallSeats smallSeats = new SmallSeats();
				planeSeat = new PlaneSeat(smallSeats);
				planeSeat.setStateIReservable(new AvailableState());
				planeSeat.setId(new SeatId(i%rowToGenerate, seatChar[i%smallSeats.getColumnCount()]));
				if(planeSeat.getId().getColumn()=='A'){
					planeSeat.setOnAisle(true);
					planeSeat.setOnWindow(true);
				}
				else if(planeSeat.getId().getColumn()=='B')
					planeSeat.setOnAisle(true);
				else if(planeSeat.getId().getColumn()=='C')
					planeSeat.setOnWindow(true);
			}
			else if(seatType == SeatType.ComfortSeat){
				ComfortSeats comfortSeats = new ComfortSeats();
				planeSeat = new PlaneSeat(comfortSeats);
				planeSeat.setStateIReservable(new AvailableState());
				planeSeat.setId(new SeatId(i%rowToGenerate, seatChar[i%comfortSeats.getColumnCount()]));
				if(planeSeat.getId().getColumn() == 'A')
					planeSeat.setOnWindow(true);
				if(planeSeat.getId().getColumn() == 'B')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'C')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'D')
					planeSeat.setOnWindow(true);
			}
			else if(seatType == SeatType.MediumSeat){
				MediumSeats mediumSeats = new MediumSeats();
				planeSeat = new PlaneSeat(mediumSeats);
				planeSeat.setStateIReservable(new AvailableState());
				planeSeat.setId(new SeatId(i%rowToGenerate, seatChar[i%mediumSeats.getColumnCount()]));
				if(planeSeat.getId().getColumn() == 'A')
					planeSeat.setOnWindow(true);
				if(planeSeat.getId().getColumn() == 'C')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'D')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'F')
					planeSeat.setOnWindow(true);
			}
			else if(seatType == SeatType.LargeSeat){
				LargeSeats largeSeats = new LargeSeats();
				planeSeat = new PlaneSeat(largeSeats);
				planeSeat.setStateIReservable(new AvailableState());
				planeSeat.setId(new SeatId(i%rowToGenerate, seatChar[i%largeSeats.getColumnCount()]));
				if(planeSeat.getId().getColumn() == 'A')
					planeSeat.setOnWindow(true);
				if(planeSeat.getId().getColumn() == 'C')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'D')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'G')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'H')
					planeSeat.setOnAisle(true);
				if(planeSeat.getId().getColumn() == 'J')
					planeSeat.setOnWindow(true);
			}
			seatList.add(planeSeat);
		}
		section.setReservableList(seatList);
	}


}