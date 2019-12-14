package ObserverPackage;

import java.util.ArrayList;
import java.util.HashMap;

public class ObserverManager {

	private java.util.HashMap<Subject, Observer> subscription = new HashMap<>();

	private static ObserverManager instance = new ObserverManager();

	private ObserverManager(){};

	public static ObserverManager getInstance() {
		return instance;
	}

	public void attach(Subject subject, Observer observer) {
		subscription.put(subject, observer);
	}

	public void detach(Subject subject,Observer observer) {
		subscription.remove(subject, observer);
	}

	public <T> void notify(Subject subject, ArrayList<T> register) {
		Observer observer = subscription.get(subject);
		if(observer != null)
			observer.updateRegister(register);
	}

	public void publish(Subject subject) {

	}

}