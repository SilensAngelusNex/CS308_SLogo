package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class RepeatCommand extends AbstractCommand {
	String myName = "Repeat";

	public RepeatCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public int maxArgs() {
		return 2;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		double result = 0;
		
		int repeatTimes = (int) getChild(0).execute(t); 
		for (int i = 0; i < repeatTimes; i++){
			result = getChild(1).execute(t);
		}
		
		return result;
	}

}
