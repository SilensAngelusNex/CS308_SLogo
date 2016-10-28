package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class GoToCommand extends AbstractCommand {
	String myName = "SetPosition";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().goTo(getChild(0).execute(), getChild(1).execute());			
	}

	@Override
	public int maxArgs() {
		return 2;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}