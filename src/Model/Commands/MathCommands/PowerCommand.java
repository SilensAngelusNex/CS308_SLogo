package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Power" command.
 * @author Weston
 *
 */
public class PowerCommand extends AbstractMathCommand {
	private static final String myName = "Power";

	public PowerCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public int maxArgs() {
		return 1;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		return Math.log(getChild(0).execute(getTurtle()));
	}

}
