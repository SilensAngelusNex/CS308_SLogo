package Model.Commands.ControlCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import Model.Commands.Command;
import parser.InvalidCommandException;

public class DoTimesCommand extends AbstractCommand {
	String myName = "DoTimes";
	
	public DoTimesCommand(CommandableModel model, ResourceBundle commands) {
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
		
		String varName = getChild(0).getChild(0).getName().replaceAll(":", "");
		int times = (int) getChild(0).getChild(1).execute();

		Command toExec = getChild(1);
		
		double result = 0;
		for (int i = 0; i < times; i++){
			getModel().set(varName, 0);
			result = toExec.execute();
		}
		
		return result;
	}

}