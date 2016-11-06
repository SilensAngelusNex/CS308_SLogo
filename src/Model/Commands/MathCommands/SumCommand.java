package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Sum" command.
 * @author Weston
 *
 */
public class SumCommand extends AbstractMathCommand {
	String myName = "Sum";

	public SumCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute(getTurtle()) + getChild(1).execute(getTurtle());
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