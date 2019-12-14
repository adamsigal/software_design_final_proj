package ModelPackages;

import java.util.Objects;

public class IBuilding {

	protected String buildingId;
	protected String city;

	public IBuilding(String buildingId, String city) {
		this.buildingId = buildingId;
		this.city = city;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object o) {
		//generated by intellij
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		IBuilding iBuilding = (IBuilding) o;
		return getBuildingId().equals(iBuilding.getBuildingId());
	}


}