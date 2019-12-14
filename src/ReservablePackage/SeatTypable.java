package ReservablePackage;

public abstract class SeatTypable {

	protected char seatId;
	protected int columnCount;

	public abstract int getColumnCount();

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public abstract char getSeatId();

	public void setSeatId(char seatId) {
		this.seatId = seatId;
	}

}