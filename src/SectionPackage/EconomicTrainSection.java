package SectionPackage;

public class EconomicTrainSection extends ITrainSection implements HalfPriceable {

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
	@Override
	public double getPriceModifier() {
		return priceModifier;
	}

}