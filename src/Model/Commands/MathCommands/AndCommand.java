package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class AndCommand extends AbstractCommand {
	String myName = "And";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() != 0 &&  getChild(1).execute() != 0 ? 1 : 0;
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