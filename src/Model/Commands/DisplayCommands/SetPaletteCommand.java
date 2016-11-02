package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
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
	protected double execCommand(TurtleModel t) throws InvalidCommandException {
		return getModel().setPallet(getChild(0).execute(t), 
									getChild(1).execute(t),
									getChild(2).execute(t),
									getChild(3).execute(t));
	}

}
