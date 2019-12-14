package AdminPackage;

import ModelPackages.CompanyRegister;
import ModelPackages.AirlineCompany;

public class DeleteAirlineCompanyCommand implements  IAdminCommand {
    private String deletedCompanyId;

    public DeleteAirlineCompanyCommand(String deletedCompanyId) {
        this.deletedCompanyId = deletedCompanyId;
    }

    @Override
    public void execute() {
        CompanyRegister.getInstance().delete(deletedCompanyId);
    }

    @Override
    public void undo() {
        if(!deletedCompanyId.equals("")){
            AirlineCompany newAirLineCompany = new AirlineCompany(deletedCompanyId);
            CompanyRegister.getInstance().add(newAirLineCompany);
            deletedCompanyId ="";
        }
    }
}