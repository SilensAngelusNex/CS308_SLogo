package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class ClearScreenCommand extends AbstractCommand{
	String myName = "ClearScreen";

	public ClearScreenCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().clearScreen();			
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
