package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.ControlCommands.ToCommand;
import parser.InvalidCommandException;

public class CommandList extends AbstractCommandList{
	
	public CommandList(CommandableModel model, ResourceBundle language) {
		super("[", model, language);
	}
	
	@Override
	public void endList() throws InvalidCommandException{
		endList("]");
		
		/*
		 * Adds the User defined command to the list of recognized commands when its list of arguments is completed.
		 *This allows for recursion in UserDefinedCommands.
		 *Probably a weird dependency that needs documentation.
		 */
		if (getParent() instanceof ToCommand && getParent().getChild(1) == this)
			((ToCommand) getParent()).recursionEnable();
	}

	@Override
	public double execute() throws InvalidCommandException {
		return execCommand();
	}
	
	@Override
	public void selfReplace(Command replacement) {
		if (getParent() != null)
			super.selfReplace(replacement);
		else {
			getChildren().clear();
			addChild(replacement);
		}
	}


	@Override
	protected double execCommand() throws InvalidCommandException {
		double result = 0;
		
		for (Command c: getChildren())
			result = c.execute();

		return result;
	}

}
