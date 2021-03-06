package Model.Commands.DisplayCommands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.Commands.AbstractCommand;
import Model.Commands.ConstantCommand;
import parser.InvalidCommandException;

/**
 * This class is used as the superclass for display commands, to remove the need to override the
 * execNonTurtleCommand method in each display command.
 * @author Weston
 *
 */
abstract public class AbstractDisplayCommand extends AbstractCommand{
	
	public AbstractDisplayCommand(CommandableModel model,ResourceBundle commands){
		super(model, commands);
	}
	
	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {
		if(!isTurtleCommand()){
			boolean childrenConstant = true;
			for (int i = 0; i < getChildren().size(); i++){
				getChild(i).execNonTurtle(getTurtle());
				childrenConstant = childrenConstant && (getChild(i) instanceof ConstantCommand);
			}
			if (childrenConstant){
				double result = execute(getTurtle());
				
				selfReplace(new ConstantCommand(this, result));
			}
		}
		
	}

}