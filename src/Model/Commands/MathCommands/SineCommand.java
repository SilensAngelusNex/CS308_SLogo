package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Sine" command.
 * @author Weston
 *
 */
public class SineCommand extends AbstractMathCommand {
	String myName = "Sine";

	public SineCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.sin(Math.toRadians(getChild(0).execute(getTurtle())));
	}

	@Override
	public int maxArgs() {
		return 1;
	}
	
	@Override
	public String getName(){
		return myName;
	}
}