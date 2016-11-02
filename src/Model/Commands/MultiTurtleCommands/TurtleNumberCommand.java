package Model.Commands.MultiTurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;

public class TurtleNumberCommand extends AbstractTurtleCommand{
	private static final String myName = "Turtles";

	public TurtleNumberCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
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
	protected double execCommand() throws InvalidCommandException {
		return getModel().turtleNumber();
	}

}