package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Right" command.
 * @author Weston
 *
 */
public class RightCommand extends AbstractTurtleCommand{
	String myName = "Right";

	public RightCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}
	@Override
	public double execCommand() throws InvalidCommandException {
		return getTurtle().right(getChild(0));
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