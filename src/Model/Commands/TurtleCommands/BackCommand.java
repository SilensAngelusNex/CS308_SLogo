package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class BackCommand extends AbstractTurtleCommand{
	String myName = "Back";

	public BackCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}
	
	@Override
	public double execCommand() throws InvalidCommandException{
		double childResult = getChild(0).execute();
		return getModel().back(childResult);
	}

	@Override
	public int maxArgs() {
		return 1;
	}
	
	@Override
	public String getName(){
		return myName;
	}


}
