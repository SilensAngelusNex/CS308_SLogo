package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class TangentCommand extends AbstractCommand {
	String myName = "Tangent";

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.tan(Math.toRadians(getChild(0).execute()));
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