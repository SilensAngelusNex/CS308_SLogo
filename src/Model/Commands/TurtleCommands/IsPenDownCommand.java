package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class IsPenDownCommand extends AbstractCommand {
	String myName = "IsPenDown";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().isPenDown();			
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