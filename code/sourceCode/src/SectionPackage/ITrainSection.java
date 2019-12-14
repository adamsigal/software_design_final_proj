package SectionPackage;

import ReservablePackage.*;

import java.util.ArrayList;

public abstract class ITrainSection extends ISeatableSection {

	protected java.util.ArrayList<TrainSeatable> reservableList;

	public ArrayList<TrainSeatable> getReservableList() {
		return reservableList;
	}

	public void setReservableList(ArrayList<TrainSeatable> reservableList) {
		this.reservableList = reservableList;
	}

	public void setPrice(float price){
		for (TrainSeatable iReservable:reservableList) {
			iReservable.setPrice(price);
		}
	}

	public float getBasePrice(){
		return reservableList.get(0).getPrice();
	}
}