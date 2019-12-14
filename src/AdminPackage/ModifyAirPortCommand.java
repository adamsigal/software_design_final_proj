package AdminPackage;

import ModelPackages.Airport;

public class ModifyAirPortCommand implements IAdminCommand {
    String newCity;
    String newId;
    Airport  airport;
    String oldId;
    String oldCity;

    public ModifyAirPortCommand(String newCity, String newId, Airport airport) {
        this.newCity = newCity;
        this.newId = newId;
        this.airport = airport;
        this.oldCity = airport.getCity();
        this.oldId = airport.getBuildingId();
    }

    @Override
    public void execute() {
        if(!newCity.equals("")){
            airport.setCity(newCity);
        }
        if(!newId.equals("")){
            airport.setBuildingId(newId);
        }
    }

    @Override
    public void undo() {
        airport.setCity(oldCity);
        airport.setBuildingId(oldId);
    }
}