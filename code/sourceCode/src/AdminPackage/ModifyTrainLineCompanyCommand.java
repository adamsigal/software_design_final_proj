package AdminPackage;

import ModelPackages.TrainLineCompany;

public class ModifyTrainLineCompanyCommand implements IAdminCommand {
    String newId;
    TrainLineCompany company;
    String oldId;

    public ModifyTrainLineCompanyCommand(String newId, TrainLineCompany company) {
        this.newId = newId;
        this.company = company;
    }

    @Override
    public void execute() {
        company.setCompanyId(newId);
    }

    @Override
    public void undo() {
        company.setCompanyId(oldId);
    }
}