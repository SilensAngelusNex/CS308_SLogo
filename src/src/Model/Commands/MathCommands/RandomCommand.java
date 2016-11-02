package Model.Commands.MathCommands;

import java.util.Random;
import java.util.ResourceBundle;

import Model.TurtleModel;
import parser.InvalidCommandException;

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
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		return myRandom.nextDouble() * getChild(0).execute(t);
	}

}
