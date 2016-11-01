package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import Model.Commands.Command;
import parser.InvalidCommandException;

abstract public class AbstractTurtleCommand extends AbstractCommand{

	public AbstractTurtleCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {
		for(Command c: getChildren()){
			c.execNonTurtle();
		}
	}
	

}
