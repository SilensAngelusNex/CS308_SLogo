package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Model.Commands.Command;

/**
 * This class handles reading commands from a file and saving commands to a
 * file.
 * 
 * @author Daniel Chai
 */
public class FileHandler {
	private static final String END_OF_FILE = "\\z";

	private MainParser myParser;

	public FileHandler(MainParser myParser) {
		this.myParser = myParser;
	}

	/**
	 * Returns the ExpressionTree for the contents in a file
	 * 
	 * @throws FileNotFoundException
	 */
	public Command getExpressionTreeFromFile(String filePath) throws FileNotFoundException {
		String fileInput = readFileToString(filePath);
		return myParser.getExpressionTreeFromCommand(fileInput);
	}

	/**
	 * Reads given file and returns its entire contents as a single string
	 */
	private String readFileToString(String filename) throws FileNotFoundException {
		Scanner input = new Scanner(new File(filename));
		input.useDelimiter(END_OF_FILE);
		String result = input.next();
		input.close();

		return result;
	}
}
