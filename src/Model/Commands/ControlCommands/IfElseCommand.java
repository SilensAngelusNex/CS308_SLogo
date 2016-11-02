package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class IfElseCommand extends AbstractCommand {
	String myName = "IfElse";
	
	public IfElseCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public int maxArgs() {
		return 3;
	}

	@Override
	public String getName() {
		return myName;
	}


	@Override
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		
		if (getChild(0).execute(t) != 0){
			return getChild(1).execute(t);
		} else {
			return getChild(2).execute(t);
		}
	}

}