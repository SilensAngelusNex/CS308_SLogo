package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class PenDownCommand extends AbstractTurtleCommand{
	String myName = "PenDown";

	public PenDownCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.setPen(true);			
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
