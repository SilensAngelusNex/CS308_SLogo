package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Quotient" command.
 * @author Weston
 *
 */
public class QuotientCommand extends AbstractMathCommand {
	String myName = "Quotient";

	public QuotientCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute(getTurtle()) / getChild(1).execute(getTurtle());
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