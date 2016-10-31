package parser;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * This class parses commands based off patterns.
 * Based off template code from Robert Duvall.
 * 
 * @author Daniel Chai
 */
public class CommandParser {
    private List<Entry<String, Pattern>> mySymbols;
    private List<Entry<String, Pattern>> myCommands;
    
    public CommandParser(Language language) {
        mySymbols = new ArrayList<Entry<String, Pattern>>();
        myCommands = new ArrayList<Entry<String, Pattern>>();
        
        addPatterns(mySymbols, ParserUtils.SYNTAX_FILE_PATH);
        addPatterns(myCommands, language.getPath());
    }
    
    /**
     * Returns the language's type associated with the given text if one exists 
     */
    public String getSymbol(String text) {
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
            	if (e.getKey().equals("Command")) {
            		return getCommand(text);
            	}
            	else {
            		return e.getKey();
            	}	
            }
        }
        
        return ParserUtils.ERROR_CODE;
    }
    
    public String tokenType(String text) {
        for (Entry<String, Pattern> e : mySymbols)
            if (match(text, e.getValue()))
            	return e.getKey();
        return ParserUtils.ERROR_CODE;  
        }
    
    private String getCommand(String text) {
        for (Entry<String, Pattern> e : myCommands) {
            if (match(text, e.getValue())) {
            	return e.getKey();
            }
        }
        
        return ParserUtils.UNKNOWN_COMMAND_CODE;
    }
    
    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
    
    /**
     * Use the Constructor that takes in a resource file name instead of using the default then addPatterns()
     * Adds the given resource file to this language's recognized types
     */
    private void addPatterns(List<Entry<String, Pattern>> patterns, String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            patterns.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }   
}
