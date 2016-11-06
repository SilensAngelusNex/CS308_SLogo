package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

/**
 * This class is a command that cannot pre-execute its children if they contain any variable commands or turtle commands.
 * Subclasses would include loops and function definitions.
 * @author Weston
 *
 */
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
