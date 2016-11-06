package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "IsPenDown" command.
 * @author Weston
 *
 */
public class IsPenDownCommand extends AbstractTurtleCommand {
	String myName = "IsPenDown";

	public IsPenDownCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getTurtle().getPenDown() ? 1 : 0;			
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