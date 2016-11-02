package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.Command;
import parser.InvalidCommandException;

public class ForCommand extends AbstractNoVariablePreExecuteCommand {
	String myName = "For";
	
	public ForCommand(CommandableModel model, ResourceBundle commands) {
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
		
		String varName = getChild(0).getChild(0).getName().replaceAll(":", "");
		int start = (int) getChild(0).getChild(1).execute(t);
		int end = (int) getChild(0).getChild(2).execute(t);
		int jump = (int) getChild(0).getChild(3).execute(t);
		
		Command toExec = getChild(1);
		
		getModel().set(varName, start);
		
		double result = 0;
		while (getModel().get(varName) < end){
			result = toExec.execute(t);
			getModel().set(varName, getModel().get(varName) + jump);
		}
		
		return result;
	}

}