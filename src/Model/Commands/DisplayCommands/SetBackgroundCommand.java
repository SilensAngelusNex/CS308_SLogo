package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "SetBackground" command 
 * @author Daniel Chai
 */
public class SetBackgroundCommand extends AbstractDisplayCommand {
	String myName = "SetBackground";
	
	public SetBackgroundCommand(CommandableModel model, ResourceBundle language) {
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
		return getModel().setBackground(getChild(0).execute(getTurtle()));
	}


}
