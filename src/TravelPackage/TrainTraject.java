package TravelPackage;

import ModelPackages.TrainStation;
import VehiculePackage.Train;

import java.util.List;

public class TrainTraject extends TrainTrajectable {

    protected java.util.List<TrainStation> trainLineSequence;
    protected Train train;

    @Override
    public Train getTrain() {
        return train;
    }

    @Override
    public List<TrainStation> getSequence() {
        return trainLineSequence;
    }

    @Override
    public void setSequence(List<TrainStation> sequence) {
        this.trainLineSequence = sequence;
    }

    @Override
    public void setTrain(Train train) {
        this.train =train;
    }

}