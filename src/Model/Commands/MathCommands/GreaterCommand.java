package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class GreaterCommand extends AbstractMathCommand {
	String myName = "GreaterThan";

	public GreaterCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() > getChild(1).execute() ? 1 : 0;
	}

	@Override
	public int maxArgs() {
		return 2;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}