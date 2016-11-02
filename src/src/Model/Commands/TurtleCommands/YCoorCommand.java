package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import parser.InvalidCommandException;

public class YCoorCommand extends AbstractTurtleCommand {
	String myName = "YCoordinate";

	public YCoorCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return t.getY();
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