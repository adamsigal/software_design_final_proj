package VehiculePackage;

import ReservablePackage.Cabine;
import ReservablePackage.ICabinable;
import ReservablePackage.IReservableID;
import SectionPackage.*;

import java.util.ArrayList;

public class CruiseShip extends IVehicule {

	private java.util.ArrayList<IBoatSection> sectionList;

	public CruiseShip(ArrayList<IBoatSection> sectionList) {

		if(sectionList != null)
			this.sectionList = sectionList;
		else
			this.sectionList = new ArrayList<>();
	}

	//@Override
	public Cabine getCabineById(IReservableID iReservableID) {
		return getCabineById(iReservableID.getId());
	}

	//@Override
	public Cabine getCabineById(String cabinID) {

		for (IBoatSection iBoatSection : sectionList) {
			for (ICabinable cabin : iBoatSection.getReservableList()) {
				if (cabin.getId().getId().equals(cabinID)){
					return (Cabine) cabin;
				}
			}
		}
		return null;
	}

	public ArrayList<IBoatSection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList<IBoatSection> sectionList) {
		this.sectionList = sectionList;
	}
	public void setPrice(float price){
		for (IBoatSection iSection:sectionList) {
			iSection.setPrice(price);
		}
	}

	public float getBasePrice(){
		return sectionList.get(0).getBasePrice();
	}

	public void addSection(IBoatSection iBoatSection){
		sectionList.add(iBoatSection);
	}

	public void removeSection(IBoatSection iBoatSection){
		sectionList.remove(iBoatSection);
	}

}