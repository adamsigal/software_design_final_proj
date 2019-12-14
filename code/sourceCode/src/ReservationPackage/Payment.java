package ReservationPackage;

import java.util.Date;

public abstract class Payment {

	private Date paymentDate;
	private float paymentAmount;
	//private int attribute;

	public Payment(Date paymentDate, float paymentAmount){
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
	}


	public Date getPaymentDate() {
		return this.paymentDate;
	}

	/**
	 *
	 * @param paymentDate
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getPaymentAmount() {
		return this.paymentAmount;
	}

	/**
	 *
	 * @param paymentAmount
	 */
	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}
