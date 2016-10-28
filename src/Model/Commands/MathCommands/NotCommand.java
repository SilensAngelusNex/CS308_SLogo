package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class NotCommand extends AbstractCommand {
	String myName = "Not";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() == 0 ? 1 : 0;
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