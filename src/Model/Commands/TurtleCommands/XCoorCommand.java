package Model.Commands.TurtleCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * This class is a command object whose execution completes the "XCoordinate" command.
 * @author Weston
 *
 */
public class XCoorCommand extends AbstractTurtleCommand {
	String myName = "XCoordinate";

	public XCoorCommand(CommandableModel model, ResourceBundle commands) {
		super(model, commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return getTurtle().getX();
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
