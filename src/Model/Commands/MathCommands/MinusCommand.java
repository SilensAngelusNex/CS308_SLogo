package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class MinusCommand extends AbstractCommand {
	String myName = "Minus";

	@Override
	public double execCommand() throws InvalidCommandException {
		return-getChild(0).execute();
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