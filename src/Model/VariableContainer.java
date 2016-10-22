package Model;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
	private Map<String, Double> myVars;
	
	public VariableContainer(){
		myVars = new HashMap<String, Double>();
	}

	public double set(String name, double val) {
		myVars.put(name, val);
		return val;
	}

	public double get(String name) {
		return myVars.get(name);
	}

}
