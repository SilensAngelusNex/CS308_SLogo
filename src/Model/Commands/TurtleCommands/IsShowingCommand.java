package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class IsShowingCommand extends AbstractCommand {
	String myName = "IsShowing";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().isShowing();
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