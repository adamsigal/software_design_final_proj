package SectionPackage;

public class OceanViewBoatSection extends IBoatSection implements ThreeQuarterPriceable {

	private int maxPerson = 2;
	private char sectionId = 'O';

	public int getMaxPerson() {
		return maxPerson;
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