package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class VariableCommand extends AbstractCommand{
	private String myName;
	
	public VariableCommand(CommandableModel model, ResourceBundle commands, String varName) {
		super(model, commands);
		myName = varName;
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
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		return getModel().get(myName);
	}

	@Override
	protected void execNonTurtleCommand(TurtleModel t) throws InvalidCommandException {
		//Do nothing.
	}

}
