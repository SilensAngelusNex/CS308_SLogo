package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "GetShape" command
 * @author Daniel Chai
 */
public class ShapeCommand extends AbstractTurtleCommand {
	String myName = "GetShape";
	
	public ShapeCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}

	@Override
	public int maxArgs() {
		return 0;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		return getTurtle().getShape();
	}

}
