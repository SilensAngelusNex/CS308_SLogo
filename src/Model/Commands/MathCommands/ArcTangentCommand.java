package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "ArcTangent" command.
 * @author Weston
 *
 */
public class ArcTangentCommand extends AbstractMathCommand {
	String myName = "ArcTangent";
	
	public ArcTangentCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.toDegrees(Math.atan(getChild(0).execute(getTurtle())));
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