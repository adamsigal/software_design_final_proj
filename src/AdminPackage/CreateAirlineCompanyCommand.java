package AdminPackage;

import ModelPackages.CompanyRegister;
import TravelPackage.PlaneManager;

public class CreateAirlineCompanyCommand implements  IAdminCommand {
    private String companyId;

    public CreateAirlineCompanyCommand(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public void execute() {
        PlaneManager.getInstance().createCompany(companyId);
    }

    @Override
    public void undo() {
        if(!companyId.equals("")){
            CompanyRegister.getInstance().delete(companyId);
            companyId = "";
        }
    }
}
