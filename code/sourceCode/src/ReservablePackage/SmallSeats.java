package ReservablePackage;

public class SmallSeats extends SeatTypable {

	protected char seatId = 'S';
	protected int columnCount = 3;

	@Override
	public int getColumnCount() {
		return this.columnCount;
	}

	@Override
	public char getSeatId() {
		return this.seatId;
	}

}