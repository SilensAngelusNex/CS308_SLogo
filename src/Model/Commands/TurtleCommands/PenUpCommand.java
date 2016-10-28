package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class PenUpCommand extends AbstractCommand{
	String myName = "PenUp";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().penUp();
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
