package UI;

import AdminPackage.*;
import ReservablePackage.*;
import SectionPackage.*;
import TravelPackage.AdminVisitor;
import TravelPackage.ITravel;
import TravelPackage.TravelRegister;
import VehiculePackage.*;

import java.util.ArrayList;
import java.util.HashMap;


public class AdminSectionController extends Controller {

	//key is the character identifying the seatType and it returns the SeatType enum corresponding
	private HashMap<Character, SeatType> seatTypeByChar;

	public AdminSectionController(){
		seatTypeByChar = new HashMap<>();
		seatTypeByChar.put(new SmallSeats().getSeatId() ,SeatType.SmallSeat);
		seatTypeByChar.put(new ComfortSeats().getSeatId(),SeatType.ComfortSeat);
		seatTypeByChar.put(new MediumSeats().getSeatId(),SeatType.MediumSeat);
		seatTypeByChar.put(new LargeSeats().getSeatId(),SeatType.LargeSeat);
	}

	/**
	 *
	 * @param id the id of the vehicule in a String
	 * @return the corresponding IVehicule object from the register of vehicules
	 */
	private IVehicule getVehicule(String id){
		return 	VehiculeRegister.getInstance().getVehicule(id);
	}

	/**
	 *
	 * @param id the id of the vehicule in a String
	 * @return the corresponding Plane object  from the register of vehicules
	 */
	private Plane getPlane(String id){
		return (Plane)getVehicule(id);
	}
	/**
	 *
	 * @param id the id of the vehicule in a String
	 * @return the corresponding Train object  from the register of vehicules
	 */
	private Train getTrain(String id){return (Train)getVehicule(id);}
	/**
	 *
	 * @param id the id of the vehicule in a String
	 * @return the corresponding CruiseShip object  from the register of vehicules
	 */
	private CruiseShip getCruiseShip(String id){return (CruiseShip) getVehicule(id);}

	/**
	 *
	 * @param planeId the id of the plane in wich you want to create a section
	 * @param iPlaneSection the section you wich to add to the plane
	 */
	private void createSection(String planeId,IPlaneSection iPlaneSection){
		Plane plane = getPlane(planeId);
		IAdminCommand command = new CreatePlaneSectionCommand(plane,iPlaneSection);
		commandManager.executeCommand(command);
	}
	/**
	 *
	 * @param trainId the id of the train in wich you want to create a section
	 * @param iTrainSection the section you wich to add to the train
	 */
	private void createSection(String trainId,ITrainSection iTrainSection){
		Train train = getTrain(trainId);
		IAdminCommand command = new CreateTrainSectionCommand(train,iTrainSection);
		commandManager.executeCommand(command);
	}
	/**
	 *
	 * @param cruiseShipId the id of the cruise ship in wich you want to create a section
	 * @param iBoatSection the section you wich to add to the cruise ship
	 */
	private void createSection(String cruiseShipId,IBoatSection iBoatSection){
		CruiseShip cruiseShip = getCruiseShip(cruiseShipId);
		IAdminCommand command = new CreateBoatSectionCommand(cruiseShip,iBoatSection);
		commandManager.executeCommand(command);
	}

	/**
	 *
	 * @param vehiculeId the id of the plane in wich you want to create an economic section
	 * @param seatType the character representing the seat type
	 */
	public void createPlaneEconSection(String vehiculeId, char seatType){
		createSection(vehiculeId,new EconomicPlaneSection(seatTypeByChar.get(seatType)));
	}
	/**
	 *
	 * @param vehiculeId the id of the plane in wich you want to create a first class section
	 * @param seatType the character representing the seat type
	 */
	public void createPlaneFirstSection(String vehiculeId,char seatType){
		createSection(vehiculeId,new FirstPlaneSection(seatTypeByChar.get(seatType)));

	}

	/**
	 *
	 * @param vehiculeId the id of the plane in wich you want to create a premium section
	 * @param seatType the character representing the seat type
	 */
	public void createPlanePremiumSection(String vehiculeId,char seatType){
		createSection(vehiculeId,new PremiumSection(seatTypeByChar.get(seatType)));

	}

	/**
	 *
	 * @param vehiculeId the id of the plane in wich you want to create a business class section
	 * @param seatType the character representing the seat type
	 */
	public void createPlaneBusinessSection(String vehiculeId,char seatType){
		createSection(vehiculeId,new BusinessSection(seatTypeByChar.get(seatType)));

	}

	/**
	 *
	 * @param vehiculeId the id of the train in wich you want to create an economic section
	 */
	public void createTrainEconSection(String vehiculeId){
		createSection(vehiculeId,new EconomicTrainSection());

	}
	/**
	 *
	 * @param vehiculeId the id of the train in wich you want to create a first class section
	 */
	public void createTrainFirstSection(String vehiculeId){
		createSection(vehiculeId,new FirstTrainSection());
	}

	/**
	 *
	 * @param vehiculeId the id of the cruise ship in wich you to create an ocean section
	 */
	public void createBoatOceanSection(String vehiculeId){
		createSection(vehiculeId,new OceanViewBoatSection());
	}
	/**
	 *
	 * @param vehiculeId the id of the cruise ship in wich you to create an interior section
	 */
	public void createBoatInteriorSection(String vehiculeId){
		createSection(vehiculeId,new InteriorBoatSection());

	}
	/**
	 *
	 * @param vehiculeId the id of the cruise ship in wich you to create a suite section
	 */
	public void createBoatSuiteSection(String vehiculeId){
		createSection(vehiculeId,new SuiteBoatSection());

	}
	/**
	 *
	 * @param vehiculeId the id of the cruise ship in wich you to create a family section
	 */
	public void createBoatFamilySection(String vehiculeId){
		createSection(vehiculeId,new FamillyBoatSection());

	}
	/**
	 *
	 * @param vehiculeId the id of the cruise ship in wich you to create a deluxe section
	 */
	public void createBoatDeluxeSection(String vehiculeId){
		createSection(vehiculeId,new DeluxeBoatSection());
	}

	/**
	 *
	 * @return an ArrayList of String that represents each possible travel in a string
	 */
	public ArrayList<String> visitITravel(){
		ArrayList<ITravel>	register = TravelRegister.getInstance().getRegister();
		ArrayList<String> result = new ArrayList<>();
		for (ITravel iTravel: register) {
			result.add(iTravel.accept(new AdminVisitor(),' '));
		}

		return result;
	}






















}
