package AdminPackage;

import ModelPackages.CompanyRegister;
import TravelPackage.TrainManager;

public class CreateTrainLineCompanyCommand implements IAdminCommand {
    private String companyId;

    public CreateTrainLineCompanyCommand(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public void execute() {

       TrainManager.getInstance().createCompany(companyId);
    }

    @Override
    public void undo() {
        if(!companyId.equals("")){
            CompanyRegister.getInstance().delete(companyId);
            companyId = "";
        }
    }
}