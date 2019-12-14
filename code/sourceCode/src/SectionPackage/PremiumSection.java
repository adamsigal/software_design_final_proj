package SectionPackage;

import ReservablePackage.SeatType;

public class PremiumSection extends IPlaneSection implements SixtyPriceable {

	private char sectionId = 'P';

	public PremiumSection(SeatType seatType) {
		super(seatType);
	}

	@Override
	public char getSectionId() {
		return sectionId;
	}

	@Override
	public double getPriceModifier() {
		return priceModifier;
	}
}