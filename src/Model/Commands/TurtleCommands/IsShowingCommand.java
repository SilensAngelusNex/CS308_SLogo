package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "IsShowing" command.
 * @author Weston
 *
 */
public class IsShowingCommand extends AbstractTurtleCommand {
	String myName = "IsShowing";

	public IsShowingCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getTurtle().getVisibility() ? 1 : 0;
	}

	@Override
	public int maxArgs() {
		return 0;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}