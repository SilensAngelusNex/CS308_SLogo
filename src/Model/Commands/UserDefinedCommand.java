package Model.Commands;

import java.util.List;
import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class UserDefinedCommand extends AbstractCommand {
	String myName;
	Command myInstructionTree;
	List<String> myArguments;
	
	public UserDefinedCommand(CommandableModel model, ResourceBundle language, Command instructions) {
		super(model, language);
		myInstructionTree = instructions;
		
	}

	@Override
	public int maxArgs() {
		return myArguments.size();
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		double[] varValues = new double[myArguments.size()];
		
		for (int i = 0; i < varValues.length; i++){
			varValues[i] = getModel().get(myArguments.get(i));
			getModel().set(myArguments.get(i), getChild(i).execute());
		}
		
		double result = myInstructionTree.execute();
		
		for (int i = 0; i < varValues.length; i++){
			getModel().set(myArguments.get(i), varValues[i]);
		}
		
		
		return result;
	}

}
