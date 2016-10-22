package parser;

/**
 * This class represents the Exception for an invalid command.
 * 
 * @author Daniel Chai
 */
public class InvalidCommandException extends Exception {

	private static final long serialVersionUID = -8903903058983619249L;

	public InvalidCommandException(String message) {
		super(message);
	}
}
