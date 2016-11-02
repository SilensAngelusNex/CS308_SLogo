package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class MakeCommand extends AbstractCommand {
	String myName = "Make";
	
	public MakeCommand(CommandableModel model, ResourceBundle commands) {
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
		
		String varName = getChild(0).getName().replaceAll(":", "");
		double varValue = getChild(1).execute(t);
		
		return getModel().set(varName, varValue);
		
	}

}