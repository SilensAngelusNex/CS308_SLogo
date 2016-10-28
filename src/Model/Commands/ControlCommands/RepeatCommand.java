package Model.Commands.ControlCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class RepeatCommand extends AbstractCommand {
	String myName = "Repeat";

	@Override
	public int maxArgs() {
		return 2;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		double result = 0;
		
		int repeatTimes = (int) getChild(0).execute(); 
		for (int i = 0; i < repeatTimes; i++){
			result = getChild(1).execute();
		}
		
		return result;
	}

}
