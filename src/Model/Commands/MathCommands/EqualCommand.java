package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Equal" command.
 * @author Weston
 *
 */
public class EqualCommand extends AbstractMathCommand {
	String myName = "Equal";
	
	public EqualCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute(getTurtle()) == getChild(1).execute(getTurtle()) ? 1 : 0;
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