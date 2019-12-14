package AdminPackage;

import SectionPackage.ITrainSection;
import VehiculePackage.Train;

public class CreateTrainSectionCommand implements IAdminCommand {
    private Train train;
    private ITrainSection section;

    public CreateTrainSectionCommand(Train train, ITrainSection section) {
        this.train = train;
        this.section = section;
    }

    @Override
    public void execute() {
        train.addSection(section);
    }

    @Override
    public void undo() {
        train.removeSection(section);
    }
}