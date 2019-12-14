package ModelPackages;

import ObserverPackage.ObserverManager;
import ObserverPackage.Subject;

import java.util.ArrayList;

public class CompanyRegister implements Subject {

	private static CompanyRegister instance = new CompanyRegister();

	public static CompanyRegister getInstance() {
		return instance;
	}

	private ArrayList<ICompany> register = new ArrayList<>();

	public ICompany get(String companyId) {
		for (ICompany iCompany:register) {
			if (iCompany.getCompanyId().compareTo(companyId)==0){
				return iCompany;
			}
		}
		throw new RuntimeException("iCompany not found");
	}


	public void delete(String companyId) {
		// TODO - implement CompanyRegister.delete
		int index = 0;
		for (ICompany iCompany:register) {
			if (iCompany.getCompanyId().compareTo(companyId)==0){
				register.remove(index);
				notifyObserver();
				return;
			}
			++index;
		}
		throw new RuntimeException("iCompany not found");

	}

	public ArrayList<ICompany> getRegister() {
		return register;
	}

	public void add(ICompany b) {
		register.add(b);
		notifyObserver();
	}

	private CompanyRegister() {

	}

	@Override
	public void notifyObserver() {
		//push notification
		ObserverManager.getInstance().notify(this, register);
	}
}