package UI;

import AdminPackage.CommandManager;

public class Controller {
	protected CommandManager commandManager= CommandManager.getInstance();

	public void undo(){
		commandManager.undo();
	}
}
