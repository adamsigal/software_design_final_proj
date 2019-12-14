package SectionPackage;

public class DeluxeBoatSection extends IBoatSection implements FullPriceable {

	private int maxPerson = 6;
	private char sectionId = 'D';

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