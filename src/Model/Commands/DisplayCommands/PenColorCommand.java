package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

/**
 * @author Daniel Chai
 */
public class PenColorCommand extends AbstractTurtleCommand {
	String myName = "GetPenColor";
	
	public PenColorCommand(CommandableModel model, ResourceBundle language) {
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
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.getPenColor();
	}
}
