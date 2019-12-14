package SectionPackage;

public class FirstTrainSection extends ITrainSection implements FullPriceable {

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
	@Override
	public double getPriceModifier() {
		return priceModifier;
	}

}