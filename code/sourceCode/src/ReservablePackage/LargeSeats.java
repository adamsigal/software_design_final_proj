package ReservablePackage;

public class LargeSeats extends SeatTypable {

	protected char seatId = 'L';
	protected int columnCount = 10;

	@Override
	public int getColumnCount() {
		return this.columnCount;
	}

	@Override
	public char getSeatId() {
		return this.seatId;
	}

}