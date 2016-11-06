package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "If" command 
 * @author Weston
 *
 */
public class IfCommand extends AbstractCommand {
	String myName = "If";
	
	public IfCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public int maxArgs() {
		return 2;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		
		if (getChild(0).execute(getTurtle()) != 0){
			return getChild(1).execute(getTurtle());
		} else {
			return 0;
		}
	}

}