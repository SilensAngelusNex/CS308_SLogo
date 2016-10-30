package Model.Commands;

import java.util.List;
import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;


abstract public class AbstractCommand implements Command{
	private List<Command> myChildren;
	private CommandableModel myModel;
	private ResourceBundle myLanguage;
	//TODO: fetch error from resource
	private String argErrorMessage = "Not enough arguments to complete command: ";
	
	public AbstractCommand(CommandableModel model, ResourceBundle language){
		myLanguage = language;
		myModel = model;
	}
	
	protected CommandableModel getModel(){
		return myModel;
	}
	
	@Override
	public double execute() throws InvalidCommandException {
		if (argsNotFull())
			//TODO: Use resource file for error message.
			throw new InvalidCommandException(argErrorMessage + myLanguage.getString(getName()));
		else {
			return execCommand();			
		}
	}

	@Override
	public void addChild(Command cmd) {
		if (argsNotFull())
			myChildren.add(cmd);
	}
	
	@Override
	public boolean argsNotFull() {
		return myChildren.size() < maxArgs() || maxArgs() < 0;
	}

	public Command getChild(int index) {
		return myChildren.get(index);
	}

	public List<Command> getChildren() {
		return myChildren;
	}
	
	abstract protected double execCommand() throws InvalidCommandException;
	

}
