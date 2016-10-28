package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class HeadingCommand extends AbstractCommand {
	String myName = "Heading";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().heading();
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