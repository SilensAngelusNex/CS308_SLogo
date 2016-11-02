package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import Model.TurtleModel;
import parser.InvalidCommandException;

public class EqualCommand extends AbstractMathCommand {
	String myName = "Equal";
	
	public EqualCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return getChild(0).execute(t) == getChild(1).execute(t) ? 1 : 0;
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