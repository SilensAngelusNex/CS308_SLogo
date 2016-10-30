package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;

import Model.CommandableModel;
import Model.Commands.*;

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
	
	public MainParser(String commandResourcePath) {
		commandParser = new CommandParser(commandResourcePath);
		numParams = new HashMap<String, Integer>();
		initNumParams();
	}
	
    /**
     * Returns the ExpressionTree for a String command
     */
    public Command getExpressionTreeFromCommand(String command, CommandableModel model) {
        return createExpressionTree(commandParser, command.split(NEWLINE), model);
    }
    
    /**
     * Returns the ExpressionTree for the contents in a file
     */
    /*
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
    */
    
    /**
     * Returns an ExpressionTree based off the input text
     */
    private Command createExpressionTree(CommandParser lang, String[] text, CommandableModel model) {
    	CommandFactory factory = new CommandFactory(ParserUtils.SYNTAX_FILE_PATH, ParserUtils.ENGLISH_FILE_PATH, model);
    	CommandList root = factory.newCommandList();
    	
    	Stack<Command> currCommands = new Stack<Command>();
    	currCommands.push(root);
    	Stack<AbstractCommandList> currLists = new Stack<AbstractCommandList>();
    	currLists.push(root);
    	
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
	                
	                if (symbol.equals("ListEnd") || symbol.equals("GroupEnd")){
	                	currLists.peek().endList(s);
	                	currLists.pop();
	                	
	                } else {
	                	Command next;
	                	if (symbol.equals("ListStart")){
	                		next = factory.newCommandList();
		                	currLists.push((AbstractCommandList) next);
		                	
	                	} else if (symbol.equals("GroupStart")){
	                		next = factory.newCommandGroup();
	                		currLists.push((AbstractCommandList) next);
		                	
		                } else {          
			                if (lang.tokenType(s).equals("Command"))
			                	next = factory.newCommand(symbol);
			                else 
			                	next = factory.newCommand(s);
			                
			                currCommands.peek().addChild(next);
		                }
			                
		                if (!currCommands.peek().argsNotFull())
		                	currCommands.pop();
		                if (next.argsNotFull())
		                	currCommands.push(next);
	                }
	                
	            }
        	}
        }
    	if (!root.argsNotFull())
    		throw new IllegalArgumentException("Too many parens!");
        root.endList();
        return root;
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
    /*
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
    */
}
