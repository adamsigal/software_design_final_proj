package VehiculePackage;

import ReservablePackage.IReservable;
import ReservablePackage.IReservableID;
import ReservablePackage.TrainSeat;
import ReservablePackage.TrainSeatable;
import VehiculePackage.ISeatableVehicule.*;
import SectionPackage.*;

import java.util.ArrayList;

public class Train extends IVehicule implements ISeatableVehicule {

	private java.util.ArrayList<ITrainSection> sectionList;


	public Train(ArrayList<ITrainSection> sectionList) {
		this.sectionList = sectionList;
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

		for (ITrainSection iTrainSection:sectionList) {
			for (TrainSeatable trainSeat:iTrainSection.getReservableList()) {
				if (trainSeat.getId().getId().equals(iReservableID)){
					return trainSeat;
				}
			}
		}
		return null;
	}

	public ArrayList<ITrainSection> getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList<ITrainSection> sectionList) {
		this.sectionList = sectionList;
	}

	public void setPrice(float price){
		for (ITrainSection iSection:sectionList) {
			iSection.setPrice(price);
		}
	}

	public float getBasePrice(){
		return sectionList.get(0).getBasePrice();
	}

	public void addSection(ITrainSection iTrainSection){
		sectionList.add(iTrainSection);
	}

	public void removeSection(ITrainSection iTrainSection){
		sectionList.remove(iTrainSection);
	}
}
