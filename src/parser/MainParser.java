package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is the entry point for parsing a command into an expression tree.
 * Based off template code from Robert Duvall.
 * 
 * @author Daniel Chai
 *
 */
public class MainParser {
	private static final String WHITESPACE = "\\p{Space}";
	
	/**
	 * Utility function that reads given file and returns its entire contents as a single string
	 */
    private static String readFileToString(String filename) throws FileNotFoundException {
        final String END_OF_FILE = "\\z";
        
        Scanner input = new Scanner(new File(filename));
        input.useDelimiter(END_OF_FILE);
        String result = input.next();
        input.close();
        
        return result;
    }
    
    /**
     * Returns a CommandParser that can parse certain patterns
     */
    private static CommandParser initCommandParser() {
    	CommandParser lang = new CommandParser();
        lang.addPatterns("resources/languages/English");
        
        return lang;
    }
    
    /**
     * Given some text, prints results of parsing it using the given language
     */
    private static void parseText(CommandParser lang, String[] text) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                System.out.println(String.format("%s : %s", s, lang.getSymbol(s)));
            }
        }
        System.out.println();
    }
     
    public static void getExpressionTreeFromCommand(String command) {
         parseText(initCommandParser(), command.split(WHITESPACE));
    }
    
    public static void getExpressionTreeFromFile(String filePath) {
        try {
        	String fileInput = readFileToString(filePath);
            parseText(initCommandParser(), fileInput.split(WHITESPACE));
        }
        catch (FileNotFoundException e) {
            System.err.println(String.format("Could not load pattern file %s", e.getMessage()));
        }
    }
    
    public static void main(String[] args) {
    	 String command = "fd 50 rt 90";
         String filePath = "data/examples/simple/forward.logo";
         
         getExpressionTreeFromCommand(command);
         getExpressionTreeFromFile(filePath);
    }
}
