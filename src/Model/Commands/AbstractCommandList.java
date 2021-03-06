package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

/**
 * An abstract class for commands with no set number of arguments.
 * @author Weston
 *
 */
abstract public class AbstractCommandList extends AbstractCommand {
	private String myName;

	public AbstractCommandList(String openParen, CommandableModel model, ResourceBundle language) {
		super(model, language);
		if (!openParen.matches("[\\[\\(]"))
			throw new IllegalArgumentException();
		myName = openParen;
	}

	/**
	 * Closes the this by adding closeParen to it.
	 * @param closeParen
	 */
	public void endList(String closeParen) {
		String newName = String.format("%s%s", myName, closeParen);
		if (!newName.matches("[\\[\\(][\\]\\)]"))
			throw new IllegalArgumentException("Mismatched or too many parens!"); 
		myName = newName;
	}
	
	abstract public void endList() throws InvalidCommandException;
	
	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {
		preExecChildren();
	}
	
	@Override
	public boolean argsNotFull() {
		return !myName.matches("[\\[\\(][\\]\\)]");
	}
	
	@Override
	public int maxArgs() {
		return -1;
	}

	@Override
	public String getName() {
		return myName;
	}

}
