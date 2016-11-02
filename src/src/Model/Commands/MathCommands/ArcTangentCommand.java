package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import Model.TurtleModel;
import parser.InvalidCommandException;

public class ArcTangentCommand extends AbstractMathCommand {
	String myName = "ArcTangent";
	
	public ArcTangentCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand(TurtleModel t) throws InvalidCommandException {
		return Math.toDegrees(Math.atan(getChild(0).execute(t)));
	}

	@Override
	public int maxArgs() {
		return 1;
	}
	
	@Override
	public String getName(){
		return myName;
	}


}