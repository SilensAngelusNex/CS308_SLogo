package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class CosineCommand extends AbstractCommand {
	String myName = "Cosine";

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.cos(Math.toRadians(getChild(0).execute()));
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