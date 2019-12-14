package SectionPackage;

import ReservablePackage.*;

import java.util.ArrayList;

public abstract class IBoatSection extends ISection {

	protected int maxPerson;
	protected int cabinCount;
	protected java.util.ArrayList<ICabinable> reservableList;
	protected char sectionId;

	public ArrayList<ICabinable> getReservableList() {
		return reservableList;
	}

	public void setReservableList(ArrayList<ICabinable> reservableList) {
		this.reservableList = reservableList;
	}

	public void setPrice(float price){
		for (ICabinable iReservable:reservableList) {
			iReservable.setPrice(price);
		}
	}

	public float getBasePrice(){
		return reservableList.get(0).getPrice();
	}

	public abstract int getMaxPerson();
	public abstract char getSectionId();
}