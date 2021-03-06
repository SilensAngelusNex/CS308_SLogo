package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Minus" command.
 * @author Weston
 *
 */
public class MinusCommand extends AbstractMathCommand {
	String myName = "Minus";
	
	public MinusCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return-getChild(0).execute(getTurtle());
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