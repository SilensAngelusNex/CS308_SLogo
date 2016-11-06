package Model.Commands.MathCommands;

import java.util.ResourceBundle;

import Model.Commands.AbstractCommand;

/**
 * This class is used so math commands don't have access to the model, since they don't need it to complete their calculations. 
 * @author Weston
 *
 */
abstract public class AbstractMathCommand extends AbstractCommand{
	
	public AbstractMathCommand(ResourceBundle commands){
		super(null, commands);
	}

}
