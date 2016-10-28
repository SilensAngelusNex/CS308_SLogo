package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class PiCommand extends AbstractCommand {
	String myName = "Pi";

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.PI;
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