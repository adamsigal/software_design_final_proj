package ReservablePackage;

import java.util.Objects;

public class IReservableID {

	protected String id;

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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		IReservableID that = (IReservableID) o;
		return getId().compareTo(that.getId())==0;
	}

}