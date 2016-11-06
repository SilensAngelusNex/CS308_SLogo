package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import Model.Commands.Command;
import parser.InvalidCommandException;

/**
 * This class is used to avoid having to override execNonTurtleCommand in each turtle command, since turtle
 * commands cannot be pre-executed, but their children may be able to be.
 * @author Weston
 *
 */
abstract public class AbstractTurtleCommand extends AbstractCommand{

	public AbstractTurtleCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}

	@Override
	public void execNonTurtleCommand() throws InvalidCommandException {
		for(Command c: getChildren()){
			c.execNonTurtle(getTurtle());
		}
	}
	

}
