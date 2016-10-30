package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
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
	private static final String VARIABLE_CODE = "Variable";
	private static final String LIST_START_CODE = "ListStart";
	private static final String LIST_END_CODE = "ListEnd";
	
	private CommandParser commandParser;
	private Map<String, Integer> numParams;
	
	private boolean onList = false;
	
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
	                
	                if (isChildrenFull(curr, lang)) {
	                	boolean allChildrenConstants = true;
	                	
	                	for (ExpressionNode child : curr.getChildren()) {
	                		if (!isInteger(child.getCommand())) {
	                			allChildrenConstants = false;
	                			curr = child;
	                		}
	                	}
	                	
	                	if (onList && allChildrenConstants) {
	                		curr = root;
	                	}
	                }
	            }
        	}
        }
        
        return new ExpressionTree(root);
    }
    
    private void addNodeToTree(ExpressionNode curr, String s, String symbol) {
    	if (symbol.equals(LIST_START_CODE)) {
    		onList = true;
    	}
    	else if (symbol.equals(LIST_END_CODE)) {
    		return;
    	}
    	else if (symbol.equals(CONSTANT_CODE) || symbol.equals(VARIABLE_CODE)) {
        	curr.addChild(new ExpressionNode(s));
        }
        else {
        	curr.addChild(new ExpressionNode(symbol));
        }
    }
    
    private boolean isChildrenFull(ExpressionNode curr, CommandParser lang) {
    	return curr.getCommand() == "" ||
    			lang.getSymbol(curr.getCommand()).equals("Variable") ||
    			lang.getSymbol(curr.getCommand()).equals("Constant") ||
    			curr.getNumOfChildren() == numParams.get(curr.getCommand());
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
    	MainParser mainParser = new MainParser(ParserUtils.ENGLISH_FILE_PATH);
    	
        String filePath = "data/examples/simple/forward.logo";
        ExpressionTree tree1 = mainParser.getExpressionTreeFromFile(filePath);
        tree1.printTree();
        
        System.out.println();
        
    	String command = "[ fd sum 50 50 bk 50 ]";
        ExpressionTree tree2 = mainParser.getExpressionTreeFromCommand(command);
        tree2.printTree();
    }
}
