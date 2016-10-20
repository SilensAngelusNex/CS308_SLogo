package parser;

/**
 * This class represents the Exception for an invalid command.
 * 
 * @author Daniel Chai
 */
public class InvalidCommandException extends Exception {
	public InvalidCommandException(String message) {
		super(message);
	}
}
