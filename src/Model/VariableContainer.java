package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that contains variables, and allows for their manipulation and access.
 * @author Weston
 *
 */
public class VariableContainer {
	private static final double DEFAULT_VAR_VALUE = 0;
	private Map<String, Double> myVars;
	
	public VariableContainer(){
		myVars = new HashMap<String, Double>();
	}

	/**
	 * Sets the variable named name to the double value val.
	 * @param name
	 * @param val
	 * @return val
	 */
	public double set(String name, double val) {
		myVars.put(name, val);
		return val;
	}

	/**
	 * Gets the value of the variable named name.
	 * @param name
	 * @return the value of the variable named name, or if name hasn't yet been set, the DEFAULT_VAR_VALUE
	 */
	public double get(String name) {
		return myVars.containsKey(name) ? myVars.get(name) : DEFAULT_VAR_VALUE;
	}

	/**
	 * Checks if a variable exists.
	 * @param name
	 * @return true if name has been previously set and not removed, false otherwise
	 */
	public boolean has(String name) {
		return myVars.containsKey(name);
	}

	/**
	 * Deletes the variable named name.
	 * @param name 
	 */
	public void remove(String name) {
		myVars.remove(name);
	}

}
