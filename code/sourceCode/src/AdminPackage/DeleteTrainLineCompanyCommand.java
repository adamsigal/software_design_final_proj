package AdminPackage;

import ModelPackages.CompanyRegister;
import ModelPackages.ICompany;
import ModelPackages.TrainLineCompany;

public class DeleteTrainLineCompanyCommand implements IAdminCommand {
    private String deletedCompanyId;

    public DeleteTrainLineCompanyCommand(String deletedCompanyId) {
        this.deletedCompanyId = deletedCompanyId;
    }

    @Override
    public void execute() {
        CompanyRegister.getInstance().delete(deletedCompanyId);
    }

    @Override
    public void undo() {
        if(!deletedCompanyId.equals("")){
            ICompany newAirLineCompany = new TrainLineCompany(deletedCompanyId);
            CompanyRegister.getInstance().add(newAirLineCompany);
            deletedCompanyId ="";
        }
    }
}