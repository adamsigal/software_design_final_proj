package AdminPackage;

import ModelPackages.Port;

public class ModifyPortCommand implements IAdminCommand {
    String newCity;
    String newId;
    Port port;
    String oldId;
    String oldCity;

    public ModifyPortCommand(String newCity, String newId, Port port) {
        this.newCity = newCity;
        this.newId = newId;
        this.port = port;
        this.oldCity = port.getCity();
        this.oldId = port.getBuildingId();
    }

    @Override
    public void execute() {
        if (!newCity.equals("")) {
            port.setCity(newCity);
        }
        if (!newId.equals("")) {
            port.setBuildingId(newId);
        }
    }

    @Override
    public void undo() {
        port.setCity(oldCity);
        port.setBuildingId(oldId);
    }
}