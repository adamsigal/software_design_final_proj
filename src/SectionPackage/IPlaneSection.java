package SectionPackage;

import ReservablePackage.*;

import java.util.ArrayList;

public abstract class IPlaneSection extends ISeatableSection {

	protected java.util.ArrayList<PlaneSeatable> reservableList;

	public ArrayList<PlaneSeatable> getReservableList() {
		return reservableList;
	}

	private SeatType seatType;

	public void setReservableList(ArrayList<PlaneSeatable> reservableList) {
		this.reservableList = reservableList;
	}

	public void setPrice(float price){
		for (PlaneSeatable iReservable:reservableList) {
			iReservable.setPrice(price);
		}
	}

	public float getBasePrice(){
		return reservableList.get(0).getPrice();
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public IPlaneSection(SeatType seatType) {
		this.seatType = seatType;
	}
}