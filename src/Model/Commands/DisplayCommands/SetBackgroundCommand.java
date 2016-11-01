package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
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
		double childResult = getChild(0).execute();
		return getModel().setBackground(childResult);
	}

	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {
		// TODO Auto-generated method stub
		
	}

}
