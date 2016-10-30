package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class MultiArgumentCommand extends AbstractCommand{
	public static final String myName = "MultiArg";
	

	public MultiArgumentCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}

	@Override
	public int maxArgs() {
		return -1;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		throw new UnsupportedOperationException();
		/*
		double startVal = getChild(1).execute();
		
		for (int i = 2; i < getChildren().size(); i++){
			Command toExec = CommandFactory.newCommand(getChild(0));
		}
		*/
	}

}
