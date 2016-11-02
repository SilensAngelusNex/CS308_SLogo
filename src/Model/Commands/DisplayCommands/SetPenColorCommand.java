package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
 * @author Daniel Chai
 */
public class SetPenColorCommand extends AbstractTurtleCommand {
	String myName = "SetPenColor";
	
	public SetPenColorCommand(CommandableModel model, ResourceBundle language) {
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
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.setPenColor(getChild(0));
	}

}
