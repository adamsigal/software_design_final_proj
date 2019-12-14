package AdminPackage;

import ModelPackages.CompanyRegister;
import ModelPackages.CruiseCompany;

public class DeleteCruiseCompanyCommand implements IAdminCommand {
    private String deletedCompanyId;

    public DeleteCruiseCompanyCommand(String deletedCompanyId) {
        this.deletedCompanyId = deletedCompanyId;
    }

    @Override
    public void execute() {
        CompanyRegister.getInstance().delete(deletedCompanyId);
    }

    @Override
    public void undo() {
        if(!deletedCompanyId.equals("")){
            CruiseCompany newCruiseCompany = new CruiseCompany(deletedCompanyId);
            CompanyRegister.getInstance().add(newCruiseCompany);
        }
    }
}