package Model.Commands.ControlCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.Command;
import Model.Commands.CommandFactory;
import Model.Commands.VariableCommand;
import parser.InvalidCommandException;;

public class ToCommand extends AbstractNoVariablePreExecuteCommand{
	private static final String myName = "MakeUserInstruction";
	
	private CommandFactory myFactory;
	private ArrayList<String> myArgNames;
	private String myCommandName;

	public ToCommand(CommandableModel model, ResourceBundle language, CommandFactory factory) {
		super(model, language);
		myFactory = factory;
	}

	@Override
	public int maxArgs() {
		return 3;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		Command ops = getChild(2);
		
		return myFactory.addUserCommand(myCommandName, myArgNames, ops);
	}
	
	public void recursionEnable() throws InvalidCommandException{
		
		myCommandName = getChild(0).getName();
		
		List<Command> args = getChild(1).getChildren();
		myArgNames = new ArrayList<String>();
		for (Command varCommand: args) {
			if (varCommand instanceof VariableCommand)
				myArgNames.add(varCommand.getName().replaceAll(":", ""));
			else
				throw new InvalidCommandException("Invalid Arguments for: " + myName);
		}
		
		myFactory.tentativeAddUserCommand(myCommandName, myArgNames);

		
	}
	
}
