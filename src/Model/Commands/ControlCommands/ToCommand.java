package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import Model.Commands.CommandFactory;
import parser.InvalidCommandException;;

public class ToCommand extends AbstractCommand{
	private static final String myName = "MakeUserInstruction";
	private CommandFactory myFactory;

	public ToCommand(CommandableModel model, ResourceBundle language, CommandFactory factory) {
		super(model, language);
		myFactory = factory;
	}

	@Override
	public int maxArgs() {
		return 3;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		throw new UnsupportedOperationException();
	}
	
}
