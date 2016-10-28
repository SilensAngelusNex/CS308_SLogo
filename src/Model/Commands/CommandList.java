package Model.Commands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class CommandList extends AbstractCommand{

	@Override
	public double execute() throws InvalidCommandException {
		return execCommand();
	}

	@Override
	public boolean argsNotFull() {
		return true;
	}

	@Override
	public int maxArgs() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public String getName() {
		return "CommandList";
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		double result = 0;
		
		for (Command c: getChildren())
			result = c.execute();

		return result;
	}

}
