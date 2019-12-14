package VehiculePackage;

import ReservablePackage.IReservable;
import ReservablePackage.IReservableID;
import ReservablePackage.PlaneSeatable;
import VehiculePackage.ISeatableVehicule.*;
import SectionPackage.*;

import java.util.ArrayList;

public class Plane extends IVehicule implements ISeatableVehicule {



	private java.util.ArrayList<IPlaneSection> sectionList;

	public Plane(ArrayList<IPlaneSection> sectionList) {
		if(sectionList != null)
			this.sectionList = sectionList;
		else
			this.sectionList = new ArrayList<>();
	}

	@Override
	public ArrayList<IReservable> getAvailableSeats() {
		return null;
	}

	@Override
	public IReservable getIReservableById(IReservableID iReservableID) {
		return getIReservableById(iReservableID.getId());
	}

	@Override
	public IReservable getIReservableById(String iReservableID) {

		for (IPlaneSection iPlaneSection : sectionList) {
			for (PlaneSeatable planeSeat : iPlaneSection.getReservableList()) {
				if (planeSeat.getId().getId().equals(iReservableID)){
					return planeSeat;
				}
			}
		}
		return null;
	}
	public ArrayList<IPlaneSection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList<IPlaneSection> sectionList) {

		this.sectionList = sectionList;
	}
	public void setPrice(float price){
		for (IPlaneSection iSection:sectionList) {
			iSection.setPrice(price);
		}
	}

	public float getBasePrice(){
		return sectionList.get(0).getBasePrice();
	}

	public void addSection(IPlaneSection iPlaneSection){
		sectionList.add(iPlaneSection);
	}

	public void removeSection(IPlaneSection iPlaneSection){
		sectionList.remove(iPlaneSection);
	}
}