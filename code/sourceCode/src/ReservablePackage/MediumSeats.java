package ReservablePackage;

public class MediumSeats extends SeatTypable {

	protected char seatId = 'M';
	protected int columnCount = 6;

	@Override
	public int getColumnCount() {
		return this.columnCount;
	}

	@Override
	public char getSeatId() {
		return this.seatId;
	}

}