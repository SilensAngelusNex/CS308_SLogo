package parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Model.Commands.Command;

/**
 * This class handles reading commands from a file and saving commands to a
 * file.
 * 
 * @author Daniel Chai
 */
public class FileHandler {
	private static final String END_OF_FILE = "\\z";
	private static final String PATH_PREFIX = "data/saved/";
	private static final String PATH_SUFFIX = ".logo";

	private MainParser myParser;
	private Map<String, String> savedFiles; 	// map of name to file path

	public FileHandler(MainParser myParser) {
		this.myParser = myParser;
		this.savedFiles = new HashMap<String, String>();
	}

	/**
	 * Returns the names of user-saved files of commands.
	 */
	public Set<String> getUserFiles() {
		return Collections.unmodifiableSet(savedFiles.keySet());
	}

	/**
	 * Saves a list of commands into a file. Returns a boolean indicating the
	 * success/failure of the operation.
	 */
	public boolean saveCommandsToFile(String name, List<String> commands) {
		try {
			String filePath = PATH_PREFIX + name + PATH_SUFFIX;
			savedFiles.put(name, filePath);

			File file = new File(filePath);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));

			for (String command : commands) {
				out.write(command + "\n");
			}
			out.close();

			return true;
		} 
		catch (IOException ex) {
			return false;
		}
	}

	/**
	 * Deletes the specified user-saved file of commands. Returns a boolean
	 * indicating the success/failure of the operation.
	 */
	public boolean deleteUserFile(String name) {
		if (savedFiles.containsKey(name)) {
			savedFiles.remove(name);

			File file = new File(savedFiles.get(name));
			file.delete();

			return true;
		}

		return false;
	}

	/**
	 * Returns the Command tree for the contents in a user-saved file.
	 * 
	 * @throws FileNotFoundException
	 * @throws InvalidCommandException 
	 */
	public Command getCommandTreeForUserFile(String name) throws FileNotFoundException, InvalidCommandException {
		return getCommandTree(savedFiles.get(name));
	}

	/**
	 * Returns the Command tree for the contents in a file.
	 * 
	 * @throws FileNotFoundException
	 * @throws InvalidCommandException 
	 */
	public Command getCommandTree(String filePath) throws FileNotFoundException, InvalidCommandException {
		String fileInput = readFileToString(filePath);
		return myParser.getExpressionTreeFromCommand(fileInput);
	}

	/**
	 * Reads given file and returns its entire contents as a single string.
	 * 
	 * @throws FileNotFoundException
	 */
	private String readFileToString(String filename) throws FileNotFoundException {
		Scanner input = new Scanner(new File(filename));
		input.useDelimiter(END_OF_FILE);
		String result = input.next();
		input.close();

		return result;
	}
}
