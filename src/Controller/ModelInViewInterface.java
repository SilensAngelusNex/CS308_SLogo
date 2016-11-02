package Controller;

import parser.InvalidCommandException;

/**
 * @author Owen Chung
 */

public interface ModelInViewInterface {
	public String parseAndExecute(String command) throws InvalidCommandException;
	public void setTurtleImage(String image);
	public void changeLanguage(String language);
}
