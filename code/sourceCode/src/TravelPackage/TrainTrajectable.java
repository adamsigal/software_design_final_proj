package TravelPackage;

import ModelPackages.*;
import VehiculePackage.*;

public abstract class TrainTrajectable extends ITravel {

	public abstract Train getTrain();
	public abstract void setTrain(Train train);
	public abstract java.util.List<TrainStation> getSequence();
	public abstract void setSequence(java.util.List<TrainStation> sequence);


}