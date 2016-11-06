package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "PenSize" command
 * @author Daniel Chai
 */
public class SetPenSizeCommand extends AbstractTurtleCommand {
	String myName = "SetPenSize";
	
	public SetPenSizeCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}

	@Override
	public int maxArgs() {
		return 1;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		return getTurtle().setPenSize(getChild(0));
	}

}
