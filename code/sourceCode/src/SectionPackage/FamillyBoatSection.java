package SectionPackage;

public class FamillyBoatSection extends IBoatSection implements NinetyPricable {

	private int maxPerson = 6;
	private char sectionId = 'F';

	@Override
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