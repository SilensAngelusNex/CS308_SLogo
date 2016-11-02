package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class IsPenDownCommand extends AbstractTurtleCommand {
	String myName = "IsPenDown";

	public IsPenDownCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.getPenDown() ? 1 : 0;			
	}

	@Override
	public int maxArgs() {
		return 0;
	}
	
	@Override
	public String getName(){
		return myName;
	}
}