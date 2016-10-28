package Model.Commands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class MultiArgumentCommand extends AbstractCommand{

	@Override
	public double execute() {
		/*
		double result = 0;
		Command toRun = myChildren.get(0);
		
		for (Command c: myChildren){
			//TODO: execute the command in to run for each argument
		}
		return result;
		*/
		throw new UnsupportedOperationException("No multiargument commands.");
		
	}

	@Override
	public boolean argsNotFull() {
		return true;
	}

	@Override
	public int maxArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		// TODO Auto-generated method stub
		return 0;
	}

}
