package TravelPackage;

import ModelPackages.BuildingRegister;
import ModelPackages.CompanyRegister;
import ModelPackages.*;
import ReservablePackage.AvailableState;
import ReservablePackage.CabinID;
import ReservablePackage.Cabine;
import ReservablePackage.ICabinable;
import SectionPackage.*;
import VehiculePackage.CruiseShip;
import VehiculePackage.IVehicule;
import VehiculePackage.VehiculeRegister;

import java.util.ArrayList;

public class CruiseManager implements  CompanyCreator, Buildable {

	private static CruiseManager instance = new CruiseManager();

	private CruiseManager() {

	}

	public static CruiseManager getInstance() {
		return instance;
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
	 * @param sequence
	 */
	public ITravel createTraject(String arrivalDate, String arrivalTime, String departureDate, String departureTime, IBuilding source, IBuilding destination, TravelId travelId, java.util.List<Port> sequence, ICompany company) {
		Cruise cruise = new Cruise();
		cruise.setArrivalDate(arrivalDate);
		cruise.setArrivalTime(arrivalTime);
		cruise.setDepartureDate(departureDate);
		cruise.setDepartureTime(departureTime);
		cruise.setSource(source);
		cruise.setDestination(destination);
		cruise.setTravelId(travelId);
		cruise.setSequence(sequence);
		cruise.setCompany(company);
		TravelRegister.getInstance().add(cruise);
		return cruise;
	}


	@Override
	public IBuilding createBuilding(String buildingID, String city) {
		Port port = new Port(buildingID,city);
		BuildingRegister.getInstance().add(port);
		return port;
	}

	@Override
	public ICompany createCompany(String companyId) {
		if(companyId.length()<6){
			System.out.println("companyId must be 6 characters");
			return null;
		}

		CruiseCompany cruiseCompany =  new CruiseCompany(companyId);
		CompanyRegister.getInstance().add(cruiseCompany);
		return cruiseCompany;
	}

	public IVehicule createVehicule() {
		ArrayList<IBoatSection> sectionList = new ArrayList<>();
		sectionList.add(new InteriorBoatSection());
		sectionList.add(new OceanViewBoatSection());
		sectionList.add(new SuiteBoatSection());
		sectionList.add(new FamillyBoatSection());
		sectionList.add(new DeluxeBoatSection());

		for(IBoatSection section : sectionList){
			populateReservable(section);
		}

		CruiseShip newCruiseShip = new CruiseShip(sectionList);
		VehiculeRegister.getInstance().addVehicule(newCruiseShip);
		return newCruiseShip;
	}

	private void populateReservable(IBoatSection section){

		ArrayList<ICabinable> cabinList = new ArrayList<>();

		for(int i=0;i<section.getMaxPerson();i++){
			CabinID cabinID= new CabinID();
			cabinID.setId(section.getSectionId()+":"+i);
			Cabine cabine = new Cabine();
			cabine.setStateIReservable(new AvailableState());
			cabine.setId(cabinID);
			cabinList.add(cabine);
		}
		section.setReservableList(cabinList);
	}
}