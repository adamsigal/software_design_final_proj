package AdminPackage;

import SectionPackage.IPlaneSection;
import VehiculePackage.Plane;

import java.util.ArrayList;

public class CreatePlaneSectionCommand implements IAdminCommand {
	private Plane plane;
	private IPlaneSection section;


	public CreatePlaneSectionCommand(Plane plane, IPlaneSection section) {
		this.plane = plane;
		this.section = section;
	}

	@Override
	public void execute() {
		plane.addSection(section);

	}

	@Override
	public void undo() {
		plane.removeSection(section);
	}
}