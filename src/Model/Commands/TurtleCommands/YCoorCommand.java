package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class YCoorCommand extends AbstractCommand {
	String myName = "YCoordinate";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().yCor();
	}

	@Override
	public int maxArgs() {
		return 0;
	}
	
	@Override
	public String getName(){
		return myName;
	}

}