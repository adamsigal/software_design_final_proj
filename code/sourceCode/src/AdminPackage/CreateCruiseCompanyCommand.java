package AdminPackage;

import ModelPackages.CompanyRegister;
import TravelPackage.CruiseManager;

public class CreateCruiseCompanyCommand implements IAdminCommand {
    private String id;

    public CreateCruiseCompanyCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
      CruiseManager.getInstance().createCompany(id);
    }

    @Override
    public void undo() {
        if(!id.equals("")){
            CompanyRegister.getInstance().delete(id);
            id="";
        }
    }
}