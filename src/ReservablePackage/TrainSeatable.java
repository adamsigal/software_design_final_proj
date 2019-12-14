package ReservablePackage;

public abstract class TrainSeatable extends ISeatableReservable {

	protected SmallSeats seatType;

	public TrainSeatable(SmallSeats seatType) {
		this.seatType = seatType;
	}
}