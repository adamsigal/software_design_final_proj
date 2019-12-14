package UI;

import java.util.HashMap;
import java.util.Scanner;

public abstract class  CLI {
	protected 	boolean isRunning = true;

	protected Scanner scanner= new Scanner(System.in);
	 protected HashMap<String, Runnable> _commands = new HashMap<>();
	 protected String displayAndResponse(String displayed){
		 display(displayed);
		 return scanner.nextLine();
	 }

	protected void display(String message){
		System.out.println(message);
	}

	protected abstract void initAvailableCommands();
	 public abstract void run();
	protected void displayHelp(){

		display("the following commands are implented : ");
		for (String keys: _commands.keySet()) {
			display(keys);
		}
	}

	/**
	 * @param message : message to query the user
	 * @return the integer entered by the user in repsonse to the query
	 */
	protected int parseIntFromResponse(String message) {

		while (true) {
			try {
				return Integer.parseInt(displayAndResponse(message));
			}
			catch (NumberFormatException e){
				display("please enter only numbers");
			}
		}

	}

}
