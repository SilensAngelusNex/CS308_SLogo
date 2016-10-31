package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class LogCommand extends AbstractMathCommand {
	private static final String myName = "NaturalLog";

	public LogCommand(ResourceBundle commands) {
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
		return Math.log(getChild(0).execute());
	}

}

