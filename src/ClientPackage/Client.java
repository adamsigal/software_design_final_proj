package ClientPackage;

import ReservablePackage.IReservableID;
import ReservablePackage.SeatId;
import ReservationPackage.Reservation;
import TravelPackage.*;

import java.util.ArrayList;

public class Client {

	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private String dateOfBirth;
	private int passportNumber;
	private String passportExpirationDate;
	private int clientId;
	private ArrayList<Reservation> reservationList;

	public ArrayList<Reservation> getReservationList() {
		return this.reservationList;
	}

	/**
	 * 
	 * @param reservationList
	 */
	public void setReservationList(java.util.ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	/**
	 * 
	 * @param iReservableID
	 * @param travelId
	 */
	public Reservation getReservationByIReservableId(IReservableID iReservableID, TravelId travelId) throws RuntimeException {

		for (Reservation reservation: reservationList) {
			if (reservation.getTravelId().equals(travelId) && reservation.getReservableId().equals(iReservableID)){
				return reservation;
			}
		}

		throw new RuntimeException("reservation does not exist");
	}


	public boolean verifyClient(String name, String adress, String email, String phoneNumber,String birthDate, int passportNum, String passportExpDate){
		return (name.equals(this.name)) && (adress.equals(this.address)) && (email.equals(this.email))
				&& (phoneNumber.equals(this.phoneNumber)) && (birthDate.equals(this.dateOfBirth))
				&& (passportNum == this.passportNumber) && (passportExpDate.equals(this.passportExpirationDate));
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getaddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setaddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPassportNumber() {
		return this.passportNumber;
	}

	/**
	 * 
	 * @param passportNumber
	 */
	public void setPassportNumber(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportExpirationDate() {
		return this.passportExpirationDate;
	}

	/**
	 * 
	 * @param passportExpirationDate
	 */
	public void setPassportExpirationDate(String passportExpirationDate) {
		this.passportExpirationDate = passportExpirationDate;
	}

	public int getClientId() {
		return clientId;
	}

	/**
	 * 
	 * @param clientId
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

}