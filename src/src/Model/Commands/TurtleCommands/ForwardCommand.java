package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class ForwardCommand extends AbstractTurtleCommand{
	String myName = "Forward";
	
	public ForwardCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}


	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.forward(getChild(0));
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
