package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class SetPenSizeCommand extends AbstractCommand {
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
		double childResult = getChild(0).execute();
		return getModel().setPenSize(childResult);
	}

}
