package SectionPackage;

import ReservablePackage.SeatType;

public class BusinessSection extends IPlaneSection implements ThreeQuarterPriceable {

	private char sectionId = 'A';

	@Override
	public char getSectionId() {
		return sectionId;
	}

	public BusinessSection(SeatType seatType) {
		super(seatType);
	}

	@Override
	public double getPriceModifier() {
		return priceModifier;
	}
}