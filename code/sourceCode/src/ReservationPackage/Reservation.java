package ReservationPackage;

import ClientPackage.Client;
import ReservablePackage.*;
import TravelPackage.*;
import VehiculePackage.CruiseShip;
import VehiculePackage.Plane;
import VehiculePackage.Train;

import java.util.Date;

public class Reservation {

	private int reservationID;
	private IReservable reservable;
	private TravelId travelId;


	private CreditCardPayment payment;

	/**
	 *
	 * @param client
	 * @param travelId
	 * @param iReservableID
	 */
	public Reservation(Client client, TravelId travelId, IReservableID iReservableID) {

		this.travelId = travelId;

		ITravel newTravel = TravelRegister.getInstance().get(travelId);
		IReservable tmpRes;

		// Get list of reservables depending on vehicle type
		if (newTravel instanceof Cruise){
			tmpRes = ( (Cruise) newTravel).getBoat().getCabineById(iReservableID);

		}
		else if (newTravel instanceof TrainTraject){
			tmpRes = ( (TrainTraject) newTravel).getTrain().getIReservableById(iReservableID);
		}
		else{ // plane
			tmpRes = ( (Flight) newTravel).getPlane().getIReservableById(iReservableID);
		}

		this.reservable = tmpRes;
		client.getReservationList().add(this);

	}



	/**
	 *
	 * @param creditCardNumber
	 * @param securityCode
	 * @param amount
	 */
	public boolean payCreditCard(String creditCardNumber, String securityCode, float amount) {
		CreditCardPayment cPay = new CreditCardPayment(creditCardNumber, new Date(), amount);
		setPayment(cPay);
		return cPay.pay(securityCode);
	}

	/**
	 *
	 * @param difference
	 */
	// retourne true si remboursé avec succès
	public boolean reimburseDifference(String securityCode, float difference) {
		// TODO - implement Reservation.reimburseDifference
		return this.payment.reimbursement(securityCode, difference);
	}

	/*
	 *
	 * @param travelId
	 * @param reservable
	 */
	public boolean change(TravelId travelId, IReservable reservable, String securityCode) {
		boolean success = true;

		// if the reservation was confirmed, we have to reimburse the difference if the new reservation is cheaper
		if (reservable.getStateIReservable() instanceof ConfirmedState){
			float diff = this.payment.getPaymentAmount() - reservable.getPrice();

			// si le nouveau voyage coûte moins cher, on rembourse la différence
			if (diff > 0){
				success = reimburseDifference(securityCode, (float) 0.9 * diff);
			}
		}

		if (!(reservable.getStateIReservable() instanceof AvailableState)){
			// make the old reservable Available once again
			this.reservable.setStateIReservable(new AvailableState());
		}

		this.setTravelId(travelId);
		this.setReservable(reservable);

		return success;
	}

	// retourne true si on a changé d'état
	public boolean cancel() {
		// TODO - implement Reservation.cancel
		if ( !(reservable.getStateIReservable() instanceof AvailableState)){
			AvailableState avail = new AvailableState();
			//avail.setDateLastChange(); TODO: besoin d'ajouter ça?
			this.reservable.setStateIReservable(avail);

			return true;
		}
		else{
			return false;
		}

	}

	// retourne true si on a changé d'état
	public boolean confirmed() {
		// TODO - implement Reservation.confirmed

		if ( !(reservable.getStateIReservable() instanceof ConfirmedState)){
			ConfirmedState conf = new ConfirmedState();

			this.reservable.setStateIReservable(conf);

			return true;
		}
		else{
			return false;
		}
	}


	public CreditCardPayment getPayment() {
		return payment;
	}

	public void setPayment(CreditCardPayment payment) {
		this.payment = payment;
	}

	public IReservable getReservable() {
		return this.reservable;
	}

	/**
	 *
	 * @param reservable
	 */
	public void setReservable(IReservable reservable) {

		this.reservable = reservable;
		reservable.setStateIReservable(new ReservedState());
	}

	public int getReservationID() {
		return this.reservationID;
	}

	/**
	 *
	 * @param reservationID
	 */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public TravelId getTravelId() {
		return travelId;
	}

	/**
	 *
	 * @param travelId
	 */
	public void setTravelId(TravelId travelId) {
		this.travelId = travelId;
	}

	public IReservableID getReservableId(){
		return reservable.getId();
	}

}
