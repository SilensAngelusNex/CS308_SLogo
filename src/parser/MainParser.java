package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import Model.CommandableModel;
import Model.Commands.*;

/**
 * This class is the entry point for parsing a command into an expression tree.
 * Based off template code from Robert Duvall.
 * 
 * @author Daniel Chai
 * @author Weston
 *
 */
public class MainParser {
	private static final String NEWLINE = "\\s*\\r?\\n\\s*";
	private static final String WHITESPACE = "\\p{Space}";
	private static final String END_OF_FILE = "\\z";
	
	private static final String COMMENT_CODE = "Comment";
	private static final String COMMAND_CODE = "Command";
	private static final String GROUP_START_CODE = "GroupStart";
	private static final String GROUP_END_CODE = "GroupEnd";
	private static final String LIST_START_CODE = "ListStart";
	private static final String LIST_END_CODE = "ListEnd";
	
	private CommandParser myParser;
	private CommandFactory myFactory;
	
	public MainParser(String commandResourcePath, CommandableModel model) {
		myParser = new CommandParser(commandResourcePath);
		myFactory = new CommandFactory(ParserUtils.SYNTAX_FILE_PATH, ParserUtils.ENGLISH_FILE_PATH, model);
	}
	
    /**
     * Returns the ExpressionTree for a String command
     */
    public Command getExpressionTreeFromCommand(String command) {
        return createExpressionTree(myParser, command.split(NEWLINE));
    }
    
    /**
     * Returns the ExpressionTree for the contents in a file
     * @throws FileNotFoundException 
     */
    public Command getExpressionTreeFromFile(String filePath) throws FileNotFoundException {
    	String fileInput = readFileToString(filePath);
    	return getExpressionTreeFromCommand(fileInput);
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
     * Returns an ExpressionTree based off the input text
     */
    private Command createExpressionTree(CommandParser lang, String[] text) {
    	CommandList root = myFactory.newCommandList();
    	
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
	                
	                if (symbol.equals(LIST_END_CODE) || symbol.equals(GROUP_END_CODE)){
	                	currLists.peek().endList(s);
	                	currLists.pop();
	                	
	                } else {
	                	Command next;
	                	if (symbol.equals(LIST_START_CODE)){
	                		next = myFactory.newCommandList();
		                	currLists.push((AbstractCommandList) next);
		                	
	                	} else if (symbol.equals(GROUP_START_CODE)){
	                		next = myFactory.newCommandGroup();
	                		currLists.push((AbstractCommandList) next);
		                	
		                } else {          
			                if (lang.tokenType(s).equals(COMMAND_CODE))
			                	next = myFactory.newCommand(symbol);
			                else 
			                	next = myFactory.newCommand(s); 
		                }  
	                	currCommands.peek().addChild(next);
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
}
