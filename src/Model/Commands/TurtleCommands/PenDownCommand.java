package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class PenDownCommand extends AbstractCommand{
	String myName = "PenDown";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().penDown();			
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
