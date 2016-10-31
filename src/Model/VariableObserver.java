package Model;

public interface VariableObserver {

	public void addVariable(String varVame, double value);
	
	public void changeVariable(String varVame, double newValue);
	
	public void deleteVariable(String varVame);
	
}
