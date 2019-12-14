package ReservablePackage;

public abstract class PlaneSeatable extends ISeatableReservable {

	protected SeatTypable seatType;

	@Override
	public SeatTypable getSeatType() {
		return seatType;
	}

	@Override
	public void setSeatType(SeatTypable seatType) {
		this.seatType = seatType;
	}

	public PlaneSeatable(SeatTypable seatType) {
		this.seatType = seatType;
	}


}