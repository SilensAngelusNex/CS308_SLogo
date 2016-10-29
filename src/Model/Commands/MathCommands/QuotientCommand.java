package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class QuotientCommand extends AbstractMathCommand {
	String myName = "Quotient";

	public QuotientCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute() / getChild(1).execute();
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