package ModelPackages;

public class ICompany {

	protected String companyId;

	public String getCompanyId(){return companyId;}
	public void setCompanyId(String newId){companyId =newId;}
	public ICompany(String companyId){
		this.companyId = companyId;
	}
}