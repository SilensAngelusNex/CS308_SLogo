package Model;

/**
 * An interface so that the SlogoModel (an Observable<VariableObserver>) can inform other objects when the variables it holds change.
 * @author Weston
 *
 */
public interface VariableObserver {

	/**
	 * Called when a variable is added to the Observable
	 * @param varVame The name of the newly added variable.
	 * @param value The value of said variable
	 */
	public void addVariable(String varVame, double value);
	
	/**
	 * Called when a variable is changed in the Observable
	 * @param varVame The name of the newly changed variable.
	 * @param value The new value of said variable
	 */
	public void changeVariable(String varVame, double newValue);
	
	/**
	 * Called when a variable is deleted from the Observable
	 * @param varVame The name of the newly deleted variable.
	 */
	public void deleteVariable(String varVame);
	
}
