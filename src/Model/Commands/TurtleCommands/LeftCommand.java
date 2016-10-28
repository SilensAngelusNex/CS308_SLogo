package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class LeftCommand extends AbstractCommand{
	String myName = "Left";

	@Override
	public double execCommand() throws InvalidCommandException {
		double childResult = getChild(0).execute();
		return getModel().left(childResult);
	}

	@Override
	public int maxArgs() {
		return 1;
	}
	
	@Override
	public String getName(){
		return myName;
	}
}
