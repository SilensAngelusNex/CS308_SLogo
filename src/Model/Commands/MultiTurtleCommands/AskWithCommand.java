package Model.Commands.MultiTurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import javafx.util.Pair;
import parser.InvalidCommandException;

public class AskWithCommand extends AbstractTurtleCommand{
	private static final String myName = "Tell";

	public AskWithCommand(CommandableModel model, ResourceBundle language) {
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
		Pair<Double, TurtleModel> result = getModel().newCompositeTurtleCondition(getChild(0));
		return getChild(1).execute(result.getValue());
	}

}