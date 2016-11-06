package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "SetShape" command 
 * @author Daniel Chai
 */
public class SetShapeCommand extends AbstractTurtleCommand {
	String myName = "SetShape";
	
	public SetShapeCommand(CommandableModel model, ResourceBundle language) {
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
		return getTurtle().setShape(getChild(0));
	}

}
