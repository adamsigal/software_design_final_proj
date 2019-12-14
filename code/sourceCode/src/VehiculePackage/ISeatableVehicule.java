package VehiculePackage;

import ReservablePackage.*;

public interface ISeatableVehicule {

	int maxRows = 100;
	int maxColumns = 10;

	java.util.ArrayList<IReservable> getAvailableSeats();

	IReservable getIReservableById(IReservableID iReservableID);
	IReservable getIReservableById(String iReservableID);

}