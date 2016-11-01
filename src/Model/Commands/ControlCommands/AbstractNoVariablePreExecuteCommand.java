package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

abstract public class AbstractNoVariablePreExecuteCommand extends AbstractCommand{

	public AbstractNoVariablePreExecuteCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}
	
	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {
		if (!(isVariableCommand() || isTurtleCommand())){
			execChildrenAndReplaceSelf();
		}
	}

}
