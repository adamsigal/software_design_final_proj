package UI;

import ClientPackage.Client;
import ClientPackage.ClientRegister;
import ObserverPackage.Observer;
import ReservationPackage.CreditCardPayment;
import ReservationPackage.Reservation;
import SectionPackage.*;
import TravelPackage.*;
import ReservablePackage.*;
import VehiculePackage.CruiseShip;
import VehiculePackage.IVehicule;
import VehiculePackage.Plane;
import VehiculePackage.Train;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ClientController implements Observer {

	private HashMap<String,Character> sectionNameToChar;
	private Client currClient;
	private ArrayList<ITravel> register;
	private static ClientController instance;

	public static ClientController getInstance() {
		return instance;
	}

	public ClientController(){
		sectionNameToChar = new HashMap<>();
		//[vehicle type][class with first char uppercase]
		sectionNameToChar.put("planePremium", new PremiumSection(null).getSectionId());
		sectionNameToChar.put("planeBusiness",new BusinessSection(null).getSectionId());
		sectionNameToChar.put("planeFirst",   new FirstPlaneSection(null).getSectionId());
		sectionNameToChar.put("planeEconomic",new EconomicPlaneSection(null).getSectionId());
		sectionNameToChar.put("trainFirst",   new FirstTrainSection().getSectionId());
		sectionNameToChar.put("trainEconomic",new EconomicTrainSection().getSectionId());
		sectionNameToChar.put("boatOceanView",new OceanViewBoatSection().getSectionId());
		sectionNameToChar.put("boatFamily",   new FamillyBoatSection().getSectionId());
		sectionNameToChar.put("boatSuite",    new SuiteBoatSection().getSectionId());
		sectionNameToChar.put("boatDeluxe",   new DeluxeBoatSection().getSectionId());
		sectionNameToChar.put("boatInterior", new InteriorBoatSection().getSectionId());
		instance = this;
	}

	public void setCurrClient(int clientId){
		this.currClient = ClientRegister.getInstance().getClient(clientId);
		if (this.currClient == null){
			System.out.println("Client ID: "  + clientId + " not found. please re-enter it.");
		}
	}

	public HashMap<String, Character> getSectionNameToChar() {
		return sectionNameToChar;
	}

	/**
	 *
	 * @param originBuildingld
	 * @param destinationBuildingId
	 * @param date
	 * @param classe
	 * @return
	 */
	public ArrayList<String> displayAvailReservables(String originBuildingld, String destinationBuildingId, String date, char classe) {
		// TODO - implement ClientAPI.displayInformationSearch
		// retrouve chaque voyage de l'origine à la destination
		ArrayList<ITravel> destList = TravelRegister.getInstance().getListFromTo(originBuildingld, destinationBuildingId);
		// une liste avec que les voyages de bonne date
		ArrayList<ITravel> dateList = new ArrayList<ITravel>();
		for(ITravel curr: destList){
			if (curr.getDepartureDate().equals(date)){
				dateList.add(curr);
			}
		}
		ArrayList<String> output = new ArrayList<>();
		for(ITravel curr:dateList){
			//visitor will filter if classe is in the ITravel or not
			output.add(curr.accept(new UserVisitor(), classe));
		}


		return output;
	}
	/*
	public java.util.ArrayList<java.util.ArrayList<SectionCount>> displayInformationSearch(String departureTraveld, String arrivalTravelId, String date, char classe) {
		// TODO - implement ClientAPI.displayInformationSearch
		throw new UnsupportedOperationException();
	}

	*/


	/**
	 *
	 * @param name
	 * @param adress
	 * @param email
	 * @param phoneNumber
	 * @param birthDate
	 * @param passportNum
	 * @param passportExpDate
	 * @param creditCardNumber
	 * @param securityCode
	 * @param reservationId
	 * @return
	 */
	public boolean paySeatCreditCard(String name, String adress, String email,String phoneNumber,String birthDate,int passportNum, String passportExpDate,int creditCardNumber, String securityCode, int reservationId) {
		// TODO - implement ClientAPI.paySeatCreditCard
		Reservation reservation = null;
		for (Reservation curr: currClient.getReservationList()){
			if (curr.getReservationID() == reservationId){
				reservation = curr;
				break;
			}
		}
		if (reservation == null){
			System.out.println("Reservation ID " + reservationId + " not found.");
			return false;
		}
		if (!currClient.verifyClient(name, adress, email, phoneNumber, birthDate, passportNum, passportExpDate)){
			System.out.println("Incorrect client credentials");
			return false;
		}


		boolean paySuccess = reservation.payCreditCard(Integer.toString(creditCardNumber), securityCode, reservation.getReservable().getPrice());

		return paySuccess;

	}

	/**
	 * @param securityCode
	 */
	public boolean changeBookedReservable(int reservationID, String newTravelID, String securityCode, char classe, boolean preferWindow){
		// TODO - implement ClientAPI.changeBookedReservable

		Reservation thisRes = null;

		for (Reservation curr: currClient.getReservationList()){
			if (curr.getReservationID() == reservationID){
				thisRes = curr;
				break;
			}
		}
		// if we don't find the entered reservation
		if (thisRes == null){
			System.out.println("Reservation ID " + reservationID + " not found.");
			return false;
		}

		IReservable newIRes = getNewIReservable(preferWindow, newTravelID, classe);

		ITravel newITravel = TravelRegister.getInstance().get(newTravelID);

		return thisRes.change(newITravel.getTravelId(), newIRes, securityCode);
	}

	/**
	 *
	 * @param reservationId
	 */
	public boolean cancelBookedReservable(int reservationId) {
		// TODO - implement ClientAPI.cancelBookedReservable

		ArrayList<Reservation> rList = currClient.getReservationList();
		for (Reservation curr: rList){
			if (curr.getReservationID() == reservationId){
				curr.cancel();
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param preferWindow
	 * @param travelId
	 * @param classe
	 * @return
	 */
	// returns a ReservationID
	public int bookReservable(boolean preferWindow, String travelId, char classe) {
		// TODO - implement ClientAPI.bookReservable

		ITravel thisITravel = TravelRegister.getInstance().get(travelId);

		if(thisITravel == null){
			return -2;
		}

		IReservable iRes = getNewIReservable(preferWindow, travelId, classe);
		if (iRes == null){
			return -1;
		}
		else{

			Reservation newReservation = new Reservation(currClient, thisITravel.getTravelId(), iRes.getId());
			currClient.getReservationList().add(newReservation);
			return newReservation.getReservationID();
		}

	}

	/**
	 *
	 * @param preferWindow
	 * @param travelId
	 * @param classe
	 * @return
	 */
	public IReservable getNewIReservable(boolean preferWindow, String travelId, char classe) {
		// TODO - implement ClientAPI.bookReservable

		ITravel newITravel = TravelRegister.getInstance().get(travelId);
		ArrayList<IReservable> resList = new ArrayList<IReservable>();

		// Get list of reservables depending on vehicle type
		if (newITravel instanceof Cruise){
			CruiseShip ship = ( (Cruise) newITravel).getBoat();

		}
		else if (newITravel instanceof TrainTraject){
			Train train = ( (TrainTraject) newITravel).getTrain();
			resList = train.getAvailableSeats();


		}
		else if(newITravel instanceof Flight){ // plane
			Plane plane = ( (Flight) newITravel).getPlane();
			resList = plane.getAvailableSeats();
		}
		else{
			return null;
		}

		// result after we filter through class and window seat preference
		ArrayList<IReservable> finalOptions = new ArrayList<>();

		// This will be the new Reservation created for the booking
		Reservation newRes;

		if(newITravel instanceof Cruise) {
			ArrayList<IBoatSection> sectionList = ((Cruise) newITravel).getBoat().getSectionList();

			ArrayList<IBoatSection> sectionListClasse = new ArrayList<>();
			for (IBoatSection curr : sectionList) {
				if (curr.getSectionId() == classe) {
					sectionListClasse.add(curr);
				}
			}


			// if an available cabin is found, it is used to create a new Reservation
			ArrayList<ICabinable> tmpResList;
			for (IBoatSection curr : sectionListClasse) {
				tmpResList = curr.getReservableList();
				for (ICabinable cabin : tmpResList) {
					if (cabin.getStateIReservable() instanceof AvailableState) {
						return cabin;
					}
				}
			}
			// if no available cabin is found, then we return -1 to represent an error
			return null;
		}
		// plane or train
		else{
			ArrayList<IReservable> resListClasse = new ArrayList<>();
			for (IReservable curr : resList){
				if( ((ISeatableReservable) curr).getSeatType().getSeatId() == classe){
					resListClasse.add(curr);
				}
			}

			ArrayList<IReservable> resListWindow = new ArrayList<>();
			for (IReservable curr : resListClasse){
				if( ((ISeatableReservable) curr).isOnWindow() == preferWindow ){
					resListWindow.add(curr);
				}
			}

			if(resListWindow.size() > 0){
				finalOptions = resListWindow;
			}
			else if(resListClasse.size() > 0){
				System.out.println("There were no options adhering to your window seat preference, however a seat was still found.");
				finalOptions = resListClasse;
			}

		}

		// if an available seat is found, it is used to create a new Reservation
		for (IReservable curr : finalOptions){
			if (curr.getStateIReservable() instanceof AvailableState){
				return curr;
			}
		}
		// if no available seat is found, then we return -1 to represent an error
		System.out.println("There were no available options matching your search.");
		return null;

	}



	@Override
	public <T> void updateRegister(ArrayList<T> register) {
		this.register= (ArrayList<ITravel>) register;
	}
}


