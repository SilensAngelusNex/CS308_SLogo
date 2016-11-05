package Model;

/**
 * An interface to allow the implementing class to be observed.
 * @author Weston
 *
 * @param <A> An observer class
 */
public interface Observable<A> {
	
	/**
	 * Adds an observer to the list of observers that are notified when events occur.
	 * @param v the observer to be added
	 */
	abstract public void addListener(A v);
	
	/**
	 * Removes an observer from the list of observers that are notified when events occur.
	 * @param v the observer to be removed
	 */
	abstract public void removeListener(A v);
}
