package ReservablePackage;

public class ComfortSeats extends SeatTypable {

	protected char seatId = 'C';
	protected int columnCount = 4;

	@Override
	public int getColumnCount() {
		return this.columnCount;
	}

	@Override
	public char getSeatId() {
		return this.seatId;
	}
}