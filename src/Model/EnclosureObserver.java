package Model;

import View.TurtleView;

/**
 * An interface to allow the implementing class to observer the Enclosure.
 * @author Weston
 *
 */
public interface EnclosureObserver {

	/**
	 * Called when turtle t is added to the Enclosure
	 * @param t
	 */
	abstract public void addTurtle(TurtleView t);
	
	/**
	 * Called when turtle t is removed from the Enclosure
	 * @param t
	 */
	abstract public void removeTurtle(TurtleView t);
	
	/**
	 * Called when turtle t is moved in the Enclosure
	 * @param t
	 */
	abstract public void moveTurtle(TurtleView t);
	
	/**
	 * Called when line l is added to the Enclosure
	 * @param l
	 */
	abstract public void addLine(LineModel l);
	
	/**
	 * Called when line l is removed from the Enclosure
	 * @param l
	 */
	abstract public void removeLine(LineModel l);
}
