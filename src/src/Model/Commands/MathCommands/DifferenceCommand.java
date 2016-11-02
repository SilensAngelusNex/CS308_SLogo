package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import Model.TurtleModel;
import parser.InvalidCommandException;

public class DifferenceCommand extends AbstractMathCommand {
	String myName = "Difference";
	
	public DifferenceCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return getChild(0).execute(t) - getChild(1).execute(t);
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