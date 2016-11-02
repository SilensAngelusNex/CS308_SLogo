package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
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
		double childResult = getChild(0).execute();
		return getModel().setShape(childResult);
	}

}
