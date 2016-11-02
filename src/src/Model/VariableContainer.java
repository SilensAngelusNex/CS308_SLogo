package Model;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
	private static final double DEFAULT_VAR_VALUE = 0;
	private Map<String, Double> myVars;
	
	public VariableContainer(){
		myVars = new HashMap<String, Double>();
	}

	public double set(String name, double val) {
		myVars.put(name, val);
		return val;
	}

	public double get(String name) {
		return myVars.containsKey(name) ? myVars.get(name) : DEFAULT_VAR_VALUE;
	}

	public boolean has(String name) {
		return myVars.containsKey(name);
	}

	public void remove(String name) {
		myVars.remove(name);
	}

}
