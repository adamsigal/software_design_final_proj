package ReservablePackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class State {

	private Date dateLastChange;

	public Date getDateLastChange() {
		return this.dateLastChange;
	}

	/**
	 * 
	 * @param dateLastChange
	 */
	public void setDateLastChange(Date dateLastChange) {
		this.dateLastChange = dateLastChange;
	}

	public State(){
		dateLastChange = new Date();
	}

}