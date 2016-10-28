package Model.Commands.ControlCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class IfCommand extends AbstractCommand {
	String myName = "If";

	@Override
	public int maxArgs() {
		return 2;
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
			return 0;
		}
	}

}