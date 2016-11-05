package Model.Commands;

import java.util.List;

import Model.TurtleModel;
import parser.InvalidCommandException;

/**
 * An interface detailing the responsibilities of Command objects
 * @author Weston
 *
 */
public interface Command {
		
	/**
	 * Executes the command.
	 * @param (TurtleModel) t the turtle executing the command
	 * @return the value the command evaluates to
	 * @throws InvalidCommandException
	 */
	abstract public double execute(TurtleModel t) throws InvalidCommandException;
	
	/**
	 * Executes the command and replaces it with a constant command if the command is not a turtle command
	 * @param (TurtleModel) t the turtle executing the command
	 * @throws InvalidCommandException
	 */
	abstract public void execNonTurtle(TurtleModel t) throws InvalidCommandException;
	
	/**
	 * Checks if the command is a turtle command
	 * @return true iff the command is a turtle command
	 */
	abstract public boolean isTurtleCommand();
	
	/**
	 * Checks if the command is a variable command
	 * @return true iff the command is a variable command
	 */
	abstract public boolean isVariableCommand();
	
	/**
	 * Adds a command to this commands list of children/arguments
	 * @param cmd
	 */
	abstract public void addChild(Command cmd);
	
	/**
	 * Replaces this command with another, by making the replacement the parent's child instead of this.
	 * @param replacement
	 */
	abstract public void selfReplace(Command replacement);
	
	/**
	 * Checks if the command needs more arguments
	 * @return true iff command can hold more arguments
	 */
	abstract public boolean argsNotFull();
	
	/**
	 * Gets the maximum arguments the command can hold
	 * @return maximum arguments
	 */
	abstract public int maxArgs();
	
	/**
	 * Gets string representation of the command. Mostly used for debugging.
	 * @return string representation
	 */
	abstract public String toString();
	
	/**
	 * Gets the command's name 
	 * @return the command's name
	 */
	abstract public String getName();
	
	/**
	 * Gets the commands children
	 * @return the list of children
	 */
	abstract public List<Command> getChildren();
	
	/**
	 * Gets child index
	 * @param index
	 * @return the child at index index
	 */
	abstract public Command getChild(int index);
	
	/**
	 * Gets the command's parent
	 * @return parent
	 */
	abstract public Command getParent();
	
	/**
	 * Sets the command's parent to cmd
	 * @param cmd
	 */
	abstract public void setParent(Command cmd);
	
	/**
	 * Sets the TurtleModel used for execution to t
	 * @param t
	 */
	abstract public void setTurtle(TurtleModel t);
	
	/**
	 * Gets the TurtleModel used for execution
	 * @return the TurtleModel used for execution
	 */
	abstract public TurtleModel getTurtle();
	
	/**
	 * Sets the TurtleModel used for execution to value, and calls setTurtleRecursive(value)
	 * on this turtle's parent
	 * @param value
	 */
	public abstract void setTurtleRecursive(TurtleModel value);
}
