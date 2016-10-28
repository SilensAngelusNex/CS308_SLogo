package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class EqualCommand extends AbstractCommand {
	String myName = "Equal";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() == getChild(1).execute() ? 1 : 0;
	}

	@Override
	public int maxArgs() {
		return 2;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}