package ObserverPackage;

import TravelPackage.ITravel;

import java.util.ArrayList;

public interface Observer {


   <T> void updateRegister(ArrayList<T> register);

}