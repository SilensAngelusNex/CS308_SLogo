package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class DifferenceCommand extends AbstractCommand {
	String myName = "Difference";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() - getChild(1).execute();
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