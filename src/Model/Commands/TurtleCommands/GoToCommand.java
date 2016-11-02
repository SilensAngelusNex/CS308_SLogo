package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class GoToCommand extends AbstractTurtleCommand {
	String myName = "SetPosition";

	public GoToCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}
	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.goTo(getChild(0), getChild(1));			
	}

	@Override
	public int maxArgs() {
		return 2;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}