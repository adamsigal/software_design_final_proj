package ReservablePackage;

import SectionPackage.*;

public class SectionCount {

	private int availableCount;
	private ISection section;

	public int getAvailableCount() {
		return this.availableCount;
	}

	/**
	 * 
	 * @param availableCount
	 */
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	public ISection getSection() {
		return this.section;
	}

	/**
	 * 
	 * @param section
	 */
	public void setSection(ISection section) {
		this.section = section;
	}

}