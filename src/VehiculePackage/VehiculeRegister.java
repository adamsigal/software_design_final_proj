package VehiculePackage;


import ObserverPackage.ObserverManager;
import ObserverPackage.Subject;

import java.util.ArrayList;

public class VehiculeRegister implements Subject {

    private static VehiculeRegister instance = new VehiculeRegister();

    public static VehiculeRegister getInstance() {
        return instance;
    }

    private ArrayList<IVehicule> vehiculeRegister = new ArrayList<>();


    public void addVehicule(IVehicule client) {
        vehiculeRegister.add(client);
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        //push notification
        ObserverManager.getInstance().notify(this, vehiculeRegister);
    }

    public ArrayList<IVehicule> getVehiculeRegister() {
        return this.vehiculeRegister;
    }

    public IVehicule getVehicule(String vehiculeId) {
        for (IVehicule vehicule : vehiculeRegister) {
            if (vehicule.getVehiculeId().compareTo(vehiculeId) == 0) {
                return vehicule;
            }
        }
        throw new RuntimeException("vehicule not found in register");

    }


    public void deleteVehicule(String vehiculeId) {
        int index = 0;
        for (IVehicule vehicule : vehiculeRegister) {
            if (vehicule.getVehiculeId().compareTo(vehiculeId) == 0) {
                vehiculeRegister.remove(index);
                notifyObserver();
                return;
            }
            ++index;
        }
    }

    private VehiculeRegister() {

    }


}
