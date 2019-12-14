package SectionPackage;

import ReservablePackage.SeatType;

public class EconomicPlaneSection extends IPlaneSection implements HalfPriceable {

	private char sectionId = 'E';

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

	public EconomicPlaneSection(SeatType seatType) {
		super(seatType);
	}
	@Override
	public double getPriceModifier() {
		return priceModifier;
	}
}