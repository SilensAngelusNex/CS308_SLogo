package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "Make/Set" command 
 * @author Weston
 *
 */
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
	protected double execCommand() throws InvalidCommandException {
		
		String varName = getChild(0).getName().replaceAll(":", "");
		double varValue = getChild(1).execute(getTurtle());
		
		return getModel().set(varName, varValue);
		
	}

}