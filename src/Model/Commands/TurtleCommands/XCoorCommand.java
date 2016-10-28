package Model.Commands.TurtleCommands;

import Model.Commands.AbstractCommand;
import parser.InvalidCommandException;

public class XCoorCommand extends AbstractCommand {
	String myName = "XCoordinate";

	@Override
	public double execCommand() throws InvalidCommandException {
		return getModel().xCor();
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
