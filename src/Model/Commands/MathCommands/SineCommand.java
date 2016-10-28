package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class SineCommand extends AbstractCommand {
	String myName = "Sine";

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.sin(Math.toRadians(getChild(0).execute()));
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