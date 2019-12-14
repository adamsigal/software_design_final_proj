package AdminPackage;

import ModelPackages.CruiseCompany;

public class ModifyCruiseCompanyCommand implements IAdminCommand {
    String newId;
    CruiseCompany company;
    String oldId;

    public ModifyCruiseCompanyCommand(String newId, CruiseCompany company) {
        this.newId = newId;
        this.company = company;
        this.oldId = company.getCompanyId();
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