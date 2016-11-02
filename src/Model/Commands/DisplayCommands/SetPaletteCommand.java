package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * @author Daniel Chai
 */
public class SetPaletteCommand extends AbstractDisplayCommand {
	String myName = "SetPalette";
	
	public SetPaletteCommand(CommandableModel model, ResourceBundle language) {
		super(model, language);
	}

	@Override
	public int maxArgs() {
		return 4;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	protected double execCommand() throws InvalidCommandException {
		return getModel().setPallet(getChild(0).execute(getTurtle()), 
									getChild(1).execute(getTurtle()),
									getChild(2).execute(getTurtle()),
									getChild(3).execute(getTurtle()));
	}

}
