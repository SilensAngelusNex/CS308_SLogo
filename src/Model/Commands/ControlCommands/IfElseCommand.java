package Model.Commands.ControlCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class IfElseCommand extends AbstractCommand {
	String myName = "IfElse";

	@Override
	public int maxArgs() {
		return 3;
	}

	@Override
	public String getName() {
		return myName;
	}


	@Override
	protected double execCommand() throws InvalidCommandException {
		
		if (getChild(0).execute() != 0){
			return getChild(1).execute();
		} else {
			return getChild(2).execute();
		}
	}

}