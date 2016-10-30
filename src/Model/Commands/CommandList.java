package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class CommandList extends AbstractCommandList{
	
	public CommandList(CommandableModel model, ResourceBundle language) {
		super("[", model, language);
	}
	
	@Override
	public void endList(){
		endList("]");
	}

	@Override
	public double execute() throws InvalidCommandException {
		return execCommand();
	}


	@Override
	protected double execCommand() throws InvalidCommandException {
		double result = 0;
		
		for (Command c: getChildren())
			result = c.execute();

		return result;
	}

}
