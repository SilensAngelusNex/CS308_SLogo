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
    	return new CommandParser("resources/languages/English");
    }
    
    /**
     * Returns an ExpressionTree based off the input text
     */
    private static ExpressionTree createExpressionTree(CommandParser lang, String[] text) {
    	ExpressionNode root = new ExpressionNode();
    	ExpressionNode curr = root;
    	

    	
        for (String line : text) {
        	
        	if (lang.getSymbol(line).equals("Comment")){
        		continue;
        	}
        	
        	for (String s : line.split(WHITESPACE)){
	            if (s.trim().length() > 0) {
	            	String symbol = lang.getSymbol(s);
	            	
	                if (symbol.equals(ParserUtils.ERROR_CODE) || symbol.equals("Command")) {
	                	continue;
	                }
	                if (symbol.equals("Constant")) {
	                	curr.addChild(new ExpressionNode(s));
	                }
	                
	                else {	// symbol is a real command
	                	curr.addChild(new ExpressionNode(symbol));
	                	curr = curr.getChild(curr.getNumOfChildren() - 1);
	                }
	            }
        	}
        }
        
        return new ExpressionTree(root.getChild(0));
    }
    
    /**
     * Returns the ExpressionTree for a String command
     */
    public static ExpressionTree getExpressionTreeFromCommand(String command) {
        return createExpressionTree(initCommandParser(), command.split("\\s*\\r?\\n\\s*"));
    }
    
    /**
     * Returns the ExpressionTree for the contents in a file
     */
    public static ExpressionTree getExpressionTreeFromFile(String filePath) {
    	try {
    		String fileInput = readFileToString(filePath);
    		return createExpressionTree(initCommandParser(), fileInput.split("\\s*\\r?\\n\\s*"));
    	}
    	catch (FileNotFoundException e) {
    		System.err.println(String.format("Could not load pattern file %s", e.getMessage()));
    		return null;
    	}
    }
    
    // used for testing purposes
    public static void main(String[] args) {
    	
        String filePath = "data/examples/simple/forward.logo";
        ExpressionTree tree2 = getExpressionTreeFromFile(filePath);
        tree2.printTree();
        
        System.out.println();
        
    	String command = "fd sum 10 sum 10 sum 10 sum 20 20";
        ExpressionTree tree1 = getExpressionTreeFromCommand(command);
        tree1.printTree();
        

        

    }
}
