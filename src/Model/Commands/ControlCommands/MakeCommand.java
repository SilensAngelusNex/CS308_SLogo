package Model.Commands.ControlCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class MakeCommand extends AbstractCommand {
	String myName = "Make";

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
		
		String varName = getChild(0).getName().replaceAll(":", "");
		double varValue = getChild(1).execute();
		
		return getModel().set(varName, varValue);
		
	}

}