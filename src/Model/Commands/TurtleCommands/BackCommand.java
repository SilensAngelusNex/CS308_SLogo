package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Back" command.
 * @author Weston
 *
 */
public class BackCommand extends AbstractTurtleCommand{
	String myName = "Backward";

	public BackCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}
	
	@Override
	public double execCommand() throws InvalidCommandException{
		return getTurtle().back(getChild(0));
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
