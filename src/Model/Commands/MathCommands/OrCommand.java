package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class OrCommand extends AbstractMathCommand {
	String myName = "Or";
	
	public OrCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() != 0 || getChild(1).execute() != 0 ? 1 : 0;
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