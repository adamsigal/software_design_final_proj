package ReservationPackage;

import ReservationPackage.Payment;

import java.util.Date;

public class CreditCardPayment extends Payment {
	private String creditCardNumber;



	public CreditCardPayment(String creditCardNumber, Date paymentDate, float paymentAmount){
		super(paymentDate, paymentAmount);
		this.creditCardNumber = creditCardNumber;
	}


	/**
	 *
	 * @param securityCode
	 */
	// puisque nous pouvons pas accéder à un vrai système bancaire, nous supposons
	// que les paiements sont toujours acceptés
	public boolean pay(String securityCode) {

		// un placeholder pour un vrai accès ầ un systềme bancaire hypothétique
		boolean bankSystemSuccess = verifyCard(securityCode);

		if (bankSystemSuccess){
			System.out.printf("Successful payment of %.2f ", this.getPaymentAmount());
			System.out.println("using card no.: " + this.getCreditCardNumber());
		}
		else{
			System.out.println("Payment unsuccessful");
		}
		return bankSystemSuccess;
	}

	/**
	 *
	 * @param securityCode
	 */
	public boolean reimbursement(String securityCode, float amount) {
		t
		this.setPaymentAmount(amount);
		this.setPaymentDate(new Date());

		boolean bankSystemSuccess = verifyCard(securityCode);

		if (bankSystemSuccess){
			System.out.printf("Successful reimbursement of %.2f ", this.getPaymentAmount());
			System.out.println("using card no.: " + this.getCreditCardNumber());
		}
		else{
			System.out.println("Reimbursement unsuccessful");
		}
		return bankSystemSuccess;
	}

	// simule une vérification de la carte de crédit avec un système bancaire
	// utiliserait le securityCode et le numéro de carte à cette fin
	public boolean verifyCard(String securityCode){
		return true;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

}
