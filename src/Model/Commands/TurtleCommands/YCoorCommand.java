package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class YCoorCommand extends AbstractCommand {
	String myName = "YCoordinate";

	public YCoorCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().yCor();
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