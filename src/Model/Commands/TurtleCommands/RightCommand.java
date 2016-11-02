package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class RightCommand extends AbstractTurtleCommand{
	String myName = "Right";

	public RightCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}
	@Override
	public double execCommand() throws InvalidCommandException {
		double childResult = getChild(0).execute();
		return getModel().right(childResult);
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