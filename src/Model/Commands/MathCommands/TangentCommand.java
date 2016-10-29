package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class TangentCommand extends AbstractMathCommand {
	String myName = "Tangent";

	public TangentCommand(ResourceBundle commands) {
		super(commands);
	}

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