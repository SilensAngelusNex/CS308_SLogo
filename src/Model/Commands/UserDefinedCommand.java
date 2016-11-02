package Model.Commands;

import java.util.List;
import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class UserDefinedCommand extends AbstractCommand {
	String myName;
	CommandFactory myFactory;
	Command myInstructionTree;
	List<String> myArguments;
	
	public UserDefinedCommand(CommandableModel model, ResourceBundle language, String name, CommandFactory maker) {
		super(model, language);
		myFactory = maker;
		myName = name;
		
		myArguments = maker.getUserArgs(myName);
		myInstructionTree = maker.getUserCommand(myName);

	}
	
	public UserDefinedCommand(CommandableModel model, ResourceBundle language, String name) {
		super(model, language);
		myName = name;
	}

	@Override
	public int maxArgs() {
		if (myArguments != null)
			return myArguments.size();
		else 
			return 0;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		if (myInstructionTree == null) {
			throw new InvalidCommandException("Command not defined: " + myName);
		}
		
		double[] varValues = new double[myArguments.size()];
		
		for (int i = 0; i < varValues.length; i++){
			varValues[i] = getModel().get(myArguments.get(i));
			getModel().set(myArguments.get(i), getChild(i).execute(getTurtle()));
		}
		
		double result = myInstructionTree.execute(getTurtle());
		
		for (int i = 0; i < varValues.length; i++){
			getModel().set(myArguments.get(i), varValues[i]);
		}
		
		
		return result;
	}

}
