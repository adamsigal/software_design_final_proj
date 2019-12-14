package AdminPackage;

import TravelPackage.TrainTraject;
import TravelPackage.TravelRegister;

public class DeleteTrainTrajectCommand implements IAdminCommand {
   TrainTraject trainTraject;

    public DeleteTrainTrajectCommand(TrainTraject trainTraject) {
        this.trainTraject = trainTraject;
    }

    @Override
    public void execute() {
        TravelRegister.getInstance().delete(trainTraject.getTravelId());
    }

    @Override
    public void undo() {
        TravelRegister.getInstance().add(trainTraject);
    }
}