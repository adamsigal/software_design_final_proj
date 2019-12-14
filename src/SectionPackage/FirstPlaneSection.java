package SectionPackage;

import ReservablePackage.SeatType;

public class FirstPlaneSection extends IPlaneSection implements FullPriceable {

	private char sectionId = 'F';

	public char getSectionId() {
		return this.sectionId;
	}

	/**
	 * 
	 * @param sectionId
	 */
	public void setSectionId(char sectionId) {
		this.sectionId = sectionId;
	}

	public FirstPlaneSection(SeatType seatType) {
		super(seatType);
	}
	@Override
	public double getPriceModifier() {
		return priceModifier;
	}
}