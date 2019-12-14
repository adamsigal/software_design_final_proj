package ModelPackages;

import ObserverPackage.ObserverManager;
import ObserverPackage.Subject;

import java.util.ArrayList;

public class BuildingRegister implements Subject {

	private static BuildingRegister instance = new BuildingRegister();

	public static BuildingRegister getInstance() {
		return instance;
	}

	private ArrayList<IBuilding> register = new ArrayList<>();

	public IBuilding get(String buildingId) {

		for (IBuilding ibuilding:register) {
			if (ibuilding.getBuildingId().compareTo(buildingId)==0){
				return ibuilding;
			}
		}
		throw new RuntimeException("Building not found in register");
	}


	public void delete(String buildingId) {
		int index = 0;
		for (IBuilding iBuilding: register) {
			if (iBuilding.getBuildingId().compareTo(buildingId)==0){
				register.remove(index);
				return;
			}
			++index;
		}
		throw new RuntimeException("Building not found in register");

	}


	public void add(IBuilding b) {

		register.add(b);
		notifyObserver();
	}

	public ArrayList<IBuilding> getRegister() {

		return register;
	}


	public boolean idIsUnique(String b) {
		for (IBuilding iBuilding: register) {
			if (iBuilding.getBuildingId().compareTo(b)==0){
				register.remove(iBuilding);
				notifyObserver();
				return false;
			}
		}
		return true;
	}

	private BuildingRegister() {

	}
	@Override
	public void notifyObserver() {
		//push notification
		ObserverManager.getInstance().notify(this, register);
	}

}