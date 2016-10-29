package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class CosineCommand extends AbstractMathCommand {
	String myName = "Cosine";
	
	public CosineCommand(ResourceBundle commands) {
		super(commands);
	}

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