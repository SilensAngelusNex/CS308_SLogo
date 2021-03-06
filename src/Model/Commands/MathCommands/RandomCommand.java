package Model.Commands.MathCommands;

import java.util.Random;
import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Random" command.
 * @author Weston
 *
 */
public class RandomCommand extends AbstractMathCommand {
	private static final String myName = "Random";
	private static final Random myRandom = new Random();

	public RandomCommand(ResourceBundle commands) {
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
		return myRandom.nextDouble() * getChild(0).execute(getTurtle());
	}

}
