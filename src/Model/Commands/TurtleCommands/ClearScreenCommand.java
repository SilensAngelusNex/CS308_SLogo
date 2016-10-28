package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class ClearScreenCommand extends AbstractCommand{
	String myName = "ClearScreen";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().clearScreen();			
	}

	@Override
	public int maxArgs() {
		return 0;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}
