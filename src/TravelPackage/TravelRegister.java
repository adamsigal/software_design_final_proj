package TravelPackage;

import ObserverPackage.ObserverManager;
import ObserverPackage.Subject;

import java.util.ArrayList;

public class TravelRegister implements Subject {

	private static TravelRegister instance = new TravelRegister();

	public static TravelRegister getInstance() {
		return instance;
	}

	private ArrayList<ITravel> register = new ArrayList<>();

	private TravelRegister() { }

	public ArrayList<ITravel> getRegister() {
		return this.register;
	}


	/**
	 * 
	 * @param travelRegister
	 */
	public void setRegister(java.util.ArrayList<ITravel> travelRegister) {
		this.register = travelRegister;
	}

	@Override
	public void notifyObserver() {
		//push notification
		ObserverManager.getInstance().notify(this, register);
	}

	/**
	 * 
	 * @param t
	 */
	public void add(ITravel t) {
		register.add(t);
		notifyObserver();
	}

	/**
	 * 
	 * @param departureBuildingId
	 * @param arrivalBuildingId
	 */
	public java.util.ArrayList<ITravel> getListFromTo(String departureBuildingId, String arrivalBuildingId) {

		ArrayList<ITravel> result = new ArrayList<>();
		for ( ITravel iTravel:register){
			boolean sameSource = iTravel.source.getBuildingId().compareTo(departureBuildingId ) ==0;
			boolean sameDestin = iTravel.destination.getBuildingId().compareTo(arrivalBuildingId ) ==0;
			if (sameSource&&sameDestin){
				result.add(iTravel);
			}
		}

		return result;
	}

	/**
	 * 
	 * @param tId
	 */
	public ITravel get(TravelId tId) {
		for (ITravel iTravel:register) {
			if (iTravel.travelId.equals(tId)){
				return iTravel;
			}
		}
		return null;
	}

	/**
	 *
	 * @param tId
	 */
	public ITravel get(String tId) {
		for (ITravel iTravel:register) {
			if (iTravel.travelId.getId().equals(tId)){
				return iTravel;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param tId
	 */
	public void delete(TravelId tId) throws RuntimeException{
		for (ITravel iTravel:register) {
			if (iTravel.travelId.equals(tId)){
				register.remove(iTravel);
				notifyObserver();
				return;
			}
		}
		throw new RuntimeException("There is no element with this travelId in the register");
	}

	/**
	 * 
	 * @param travelId
	 */
	public boolean isTravelIdUnique(TravelId travelId) {
		//throw new UnsupportedOperationException();
		for (ITravel iTravel:register) {
			if (iTravel.travelId.equals(travelId)){
				return false;
			}
		}
		return true;
	}


}