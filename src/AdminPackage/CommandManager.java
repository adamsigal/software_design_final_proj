package AdminPackage;

import java.util.HashMap;
import java.util.Stack;

public class CommandManager {

	private Stack commandStack;
	private static CommandManager instance  = new CommandManager();

	private CommandManager(){

		commandStack = new Stack<IAdminCommand>();
	}

	public static CommandManager getInstance(){
		return instance;
	}

	public void executeCommand(IAdminCommand c) {
		c.execute();
		try{
		commandStack.push(c);
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

	public void undo() {
	    try{
            ((IAdminCommand)commandStack.pop()).undo();
        }catch (Exception ex){
	    	System.out.println(ex.getMessage());
	        ex.printStackTrace();
        }

	}

}