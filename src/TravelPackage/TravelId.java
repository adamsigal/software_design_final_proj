package TravelPackage;

import java.util.Objects;

public class TravelId {

	protected String id;
	public TravelId(String travelId){
		id = travelId;
	}
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		//generated by intellij
		if (this == o) return true;
		//System.out.println(this.getId() + " " +this.getClass()+" and o is " + o +" " +o.getClass());
		if (o == null || getClass() != o.getClass()) return false;
		TravelId travelId = (TravelId) o;
		return getId().compareTo(travelId.getId()) ==0;
	}

}