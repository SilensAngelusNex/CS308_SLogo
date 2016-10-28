package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class ShowTurtleCommand extends AbstractCommand{
	String myName = "ShowTurtle";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().showTurtle();
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
