package Model.Commands.ControlCommands;

import Model.Commands.AbstractCommand;
import Model.Commands.Command;
import parser.InvalidCommandException;

public class ForCommand extends AbstractCommand {
	String myName = "For";

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
		
		String varName = getChild(0).getChild(0).getName().replaceAll(":", "");
		int start = (int) getChild(0).getChild(1).execute();
		int end = (int) getChild(0).getChild(2).execute();
		int jump = (int) getChild(0).getChild(3).execute();
		
		Command toExec = getChild(1);
		
		getModel().set(varName, start);
		
		double result = 0;
		while (getModel().get(varName) < end){
			result = toExec.execute();
			getModel().set(varName, getModel().get(varName) + jump);
		}
		
		return result;
	}

}