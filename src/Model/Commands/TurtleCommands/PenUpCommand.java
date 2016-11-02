package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class PenUpCommand extends AbstractTurtleCommand{
	String myName = "PenUp";

	public PenUpCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().penUp();
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
