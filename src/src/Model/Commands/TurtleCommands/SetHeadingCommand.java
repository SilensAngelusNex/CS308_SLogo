package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class SetHeadingCommand extends AbstractTurtleCommand{
	String myName = "SetHeading";

	public SetHeadingCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.setHeading(getChild(0));
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