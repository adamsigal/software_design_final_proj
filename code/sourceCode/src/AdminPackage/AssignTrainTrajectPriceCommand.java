package AdminPackage;

import TravelPackage.TrainLineId;
import TravelPackage.TrainTraject;
import TravelPackage.TravelId;
import TravelPackage.TravelRegister;
import VehiculePackage.Train;

public class AssignTrainTrajectPriceCommand implements IAdminCommand {
    float oldPrice,newPrice;
    TrainLineId modifiedTravel;

    public AssignTrainTrajectPriceCommand(float newPrice, TrainLineId modifiedTravel) {
        this.newPrice = newPrice;
        this.modifiedTravel = modifiedTravel;
    }

    @Override
    public void execute() {
        Train train = ((TrainTraject) TravelRegister.getInstance().get(modifiedTravel)).getTrain();

        oldPrice = train.getBasePrice();
        train.setPrice(newPrice);
    }


    @Override
    public void undo() {

        if (oldPrice != 0f && modifiedTravel != null) {
            Train train = ((TrainTraject)TravelRegister.getInstance().get(modifiedTravel)).getTrain();
            train.setPrice(oldPrice);

            oldPrice = 0f;
            modifiedTravel = null;
        }
    }
}