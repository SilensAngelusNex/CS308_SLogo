package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

/**
 * @author Daniel Chai
 */
public class PenColorCommand extends AbstractCommand {
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
	protected double execCommand() throws InvalidCommandException {
		return getModel().getPenColor();
	}
}
