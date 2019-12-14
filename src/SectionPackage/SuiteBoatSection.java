package SectionPackage;

public class SuiteBoatSection extends IBoatSection implements NinetyPricable {

	private int maxPerson = 5;
	private char sectionId = 'S';

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