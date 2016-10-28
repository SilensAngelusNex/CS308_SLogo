package Model.Commands.MathCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class ArcTangentCommand extends AbstractCommand {
	String myName = "ArcTangent";

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.toDegrees(Math.atan(getChild(0).execute()));
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