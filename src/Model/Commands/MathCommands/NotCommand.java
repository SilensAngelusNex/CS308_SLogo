package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Not" command.
 * @author Weston
 *
 */
public class NotCommand extends AbstractMathCommand {
	String myName = "Not";
	
	public NotCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getChild(0).execute(getTurtle()) == 0 ? 1 : 0;
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