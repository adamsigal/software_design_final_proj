package AdminPackage;

import ModelPackages.AirlineCompany;

public class ModifyAirLineCompanyCommand implements IAdminCommand {
    String newId;
    AirlineCompany company;
    String oldId;

    public ModifyAirLineCompanyCommand(String newId, AirlineCompany company) {
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