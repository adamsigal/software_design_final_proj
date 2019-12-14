package SectionPackage;

public class InteriorBoatSection extends IBoatSection implements HalfPriceable {

	private int maxPerson = 4;
	private char sectionId = 'I';

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