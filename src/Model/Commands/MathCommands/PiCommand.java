package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import parser.InvalidCommandException;

public class PiCommand extends AbstractMathCommand {
	String myName = "Pi";
	
	public PiCommand(ResourceBundle commands) {
		super(commands);
	}

	@Override
	public double execCommand() throws InvalidCommandException {
		return Math.PI;
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