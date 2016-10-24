package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;

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
	private Map<String, Integer> numParams;
	
	public MainParser(String commandResourcePath) {
		commandParser = new CommandParser(commandResourcePath);
		numParams = new HashMap<String, Integer>();
		initNumParams();
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
        	
        	for (String s : line.split(WHITESPACE)) {
	            if (s.trim().length() > 0) {
	            	String symbol = lang.getSymbol(s);
	            	
	                if (symbol.equals(ParserUtils.ERROR_CODE)) {
	                	continue;
	                }
	                
	                addNodeToTree(curr, s, symbol);
	                
	                if (isChildrenFull(curr)) {
	                	for (ExpressionNode child : curr.getChildren()) {
	                		if (!isInteger(child.getCommand())) {
	                			curr = child;
	                		}
	                	}
	                }
	            }
        	}
        }
        
        return new ExpressionTree(root);
    }
    
    private void addNodeToTree(ExpressionNode curr, String s, String symbol) {
    	if (symbol.equals(CONSTANT_CODE)) {
        	curr.addChild(new ExpressionNode(s));
        }
        else {
        	curr.addChild(new ExpressionNode(symbol));
        }
    }
    
    private boolean isChildrenFull(ExpressionNode curr) {
    	return curr.getCommand() == null || curr.getNumOfChildren() == numParams.get(curr.getCommand());
    }
    
    private boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch (Exception e) { 
            return false; 
        } 
        return true;
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
    
    /**
     * Initializes the numParams map based on resource file
     */
    private void initNumParams() {
    	ResourceBundle resources = ResourceBundle.getBundle(ParserUtils.NUM_PARAMS_PATH);
        Enumeration<String> iter = resources.getKeys();
        
        while (iter.hasMoreElements()) {
            String command = iter.nextElement();
            Integer n = Integer.parseInt(resources.getString(command));
            numParams.put(command, n);
        }
    }
   
    // used for testing purposes
    public static void main(String[] args) {
    	MainParser mainParser = new MainParser("resources/languages/English");
    	
        String filePath = "data/examples/simple/forward.logo";
        ExpressionTree tree1 = mainParser.getExpressionTreeFromFile(filePath);
        tree1.printTree();
        
        System.out.println();
        
    	String command = "fd sum sum 100 100 sum 100 100";
        ExpressionTree tree2 = mainParser.getExpressionTreeFromCommand(command);
        tree2.printTree();
    }
}
