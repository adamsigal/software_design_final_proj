package ReservablePackage;

public abstract class IReservable extends IReservableContext {

	protected float price;
	protected IReservableID id;

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public IReservableID getId() {
		return id;
	}

	public void setId(IReservableID id) {
		this.id = id;
	}
}