package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Remainder" command.
 * @author Weston
 *
 */
public class RemainderCommand extends AbstractMathCommand {
	private static final String myName = "Remainder";

	public RemainderCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public int maxArgs() {
		return 2;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		return getChild(0).execute(getTurtle()) % getChild(1).execute(getTurtle());
	}

}
