package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Forward" command.
 * @author Weston
 *
 */
public class ForwardCommand extends AbstractTurtleCommand{
	String myName = "Forward";
	
	public ForwardCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}


	@Override
	public double execCommand() throws InvalidCommandException {
		return getTurtle().forward(getChild(0));
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
