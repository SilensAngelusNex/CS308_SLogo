package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class HideTurtleCommand extends AbstractCommand{
	String myName = "HideTurtle";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().hideTurtle();
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
