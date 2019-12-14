package ReservablePackage;

public abstract class ISeatableReservable extends IReservable {

	protected SeatId id;
	protected SeatTypable seatType;
	protected boolean isOnAisle = false;
	protected boolean isOnWindow = false;

	@Override
	public SeatId getId() {
		return id;
	}

	public void setId(SeatId id) {
		this.id = id;
	}

	public SeatTypable getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatTypable seatType) {
		this.seatType = seatType;
	}

	public boolean isOnAisle() {
		return isOnAisle;
	}

	public void setOnAisle(boolean onAisle) {
		isOnAisle = onAisle;
	}

	public boolean isOnWindow() {
		return isOnWindow;
	}

	public void setOnWindow(boolean onWindow) {
		isOnWindow = onWindow;
	}
}