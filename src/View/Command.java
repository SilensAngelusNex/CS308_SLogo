package View;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * @author Owen Chung
 */
public class Command {
	private SimpleStringProperty myCommand;
	private SimpleIntegerProperty myNumberOfArguments;

	public Command(String command, int numargs) {
		myCommand = new SimpleStringProperty(command);
		myNumberOfArguments = new SimpleIntegerProperty(numargs);  
	}

	/**
	 * Purpose: to get the string command
	 * @return myCommand.get();
	 */
	public String getCommandString() {
		return myCommand.get();
	}

	/**
	 * Purpose: Sets the string command
	 * @param variable
	 */
	public void setCommand(String variable){
		myCommand.set(variable);
	}

	/**
	 * Get the number of arguments of the command
	 * @return myNumberOfArguments.get()
	 */
	public int getNumofArguments() {
		return myNumberOfArguments.get();
	}

	/**
	 * Purpose: sets the number of arguments of a command
	 * @param value
	 */
	public void setNumofArguments(int value) {
		myNumberOfArguments.set(value);
	}

	/**
	 * Purpose: To determine if a certain object is equal to the command.
	 * Assumptions: That the object can be cast as a command
	 * @return boolean of if the object is equal to the command or not
	 */
	@Override
	public boolean equals(Object o){
		return (this.getNumofArguments() == ((Command)o).getNumofArguments() && 
				this.getCommandString().equals(((Command)o).getCommandString()));

	}

}
