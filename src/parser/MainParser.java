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
	private static final String NEWLINE = "\\s*\\r?\\n\\s*";
	private static final String WHITESPACE = "\\p{Space}";
	private static final String END_OF_FILE = "\\z";
	
	private static final String COMMENT_CODE = "Comment";
	private static final String CONSTANT_CODE = "Constant";
	
	private CommandParser commandParser;
	
	public MainParser(String commandResourcePath) {
		commandParser = new CommandParser(commandResourcePath);
	}
	
    /**
     * Returns the ExpressionTree for a String command
     */
    public ExpressionTree getExpressionTreeFromCommand(String command) {
        return createExpressionTree(commandParser, command.split(NEWLINE));
    }
    
    /**
     * Returns the ExpressionTree for the contents in a file
     */
    public ExpressionTree getExpressionTreeFromFile(String filePath) {
    	try {
    		String fileInput = readFileToString(filePath);
    		return createExpressionTree(commandParser, fileInput.split(NEWLINE));
    	}
    	catch (FileNotFoundException e) {
    		System.err.println(String.format("Could not load pattern file %s", e.getMessage()));
    		return null;
    	}
    }
    
    /**
     * Returns an ExpressionTree based off the input text
     */
    private ExpressionTree createExpressionTree(CommandParser lang, String[] text) {
    	ExpressionNode root = new ExpressionNode();
    	ExpressionNode curr = root;
    	
        for (String line : text) {
        	if (lang.getSymbol(line).equals(COMMENT_CODE)) {
        		continue;
        	}
        	
        	for (String s : line.split(WHITESPACE)){
	            if (s.trim().length() > 0) {
	            	String symbol = lang.getSymbol(s);
	            	
	                if (symbol.equals(ParserUtils.ERROR_CODE)) {
	                	continue;
	                }
	                
	                if (symbol.equals(CONSTANT_CODE)) {
	                	curr.addChild(new ExpressionNode(s));
	                }
	                
	                else {	// symbol is a real command
	                	curr.addChild(new ExpressionNode(symbol));
	                	curr = curr.getChild(curr.getNumOfChildren() - 1);
	                }
	            }
        	}
        }
        
        return new ExpressionTree(root);
    }
    
	/**
	 * Utility function that reads given file and returns its entire contents as a single string
	 */
    private String readFileToString(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename));
        input.useDelimiter(END_OF_FILE);
        String result = input.next();
        input.close();
        
        return result;
    }
   
    // used for testing purposes
    public static void main(String[] args) {
    	MainParser mainParser = new MainParser("resources/languages/English");
    	
        String filePath = "data/examples/simple/forward.logo";
        ExpressionTree tree2 = mainParser.getExpressionTreeFromFile(filePath);
        tree2.printTree();
        
        System.out.println();
        
    	String command = "fd sum 10 sum 10 sum 10 sum 20 20";
        ExpressionTree tree1 = mainParser.getExpressionTreeFromCommand(command);
        tree1.printTree();
    }
}
