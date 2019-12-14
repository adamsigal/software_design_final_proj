package ReservablePackage;

public abstract class IReservableContext {

	protected State stateIReservable;

	public State getStateIReservable() {
		return this.stateIReservable;
	}

	/**
	 *
	 * @param stateIReservable
	 */
	public void setStateIReservable(State stateIReservable) {
		this.stateIReservable = stateIReservable;
	}

}
