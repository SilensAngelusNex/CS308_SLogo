package Model;

import java.util.List;

/**
 * An interface to allow the implementing class to observer user defined commands
 * @author Weston
 *
 */
public interface CommandObserver {
	
	/**
	 * Called when a command named commandName with numArgs arguments named in args is defined.
	 * @param commandName
	 * @param args
	 * @param numArgs
	 */
	public void addCommand(String commandName, List<String> args, int numArgs);
}
