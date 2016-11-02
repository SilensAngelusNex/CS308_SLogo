package Model.Commands.MultiTurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import javafx.util.Pair;
import parser.InvalidCommandException;

public class TellCommand extends AbstractTurtleCommand{
	private static final String myName = "Tell";

	public TellCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
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
		Pair<Double, TurtleModel> result = getModel().newCompositeTurtle(getChild(0));
		getParent().setTurtleRecursive(result.getValue());
		return result.getKey();
	}

}