package Model;

import parser.InvalidCommandException;
import Model.Commands.Command;
import View.TurtleView;

/**
 * The part of the turtle that can be commanded by the Command objects, or by the SlogoView or Enclosure.
 * @author Weston
 *
 */
public interface TurtleModel {
	
	/**
	 * Moves forward a distance equal to the value that executing distance returns.
	 * @param (Command) distance
	 * @return the distance traveled
	 * @throws InvalidCommandException
	 */
	public abstract double forward(Command distance) throws InvalidCommandException;

	/**
	 * Moves backward a distance equal to the value that executing distance returns.
	 * @param (Command) distance
	 * @return the distance traveled
	 * @throws InvalidCommandException
	 */
	public abstract double back(Command distance) throws InvalidCommandException;

	/**
	 * Turns right a number of degrees equal to the value that executing degrees returns.
	 * @param degrees
	 * @return the degrees turned
	 * @throws InvalidCommandException
	 */
	public abstract double right(Command degrees) throws InvalidCommandException;

	/**
	 * Turns left a number of degrees equal to the value that executing degrees returns.
	 * @param degrees
	 * @return the degrees turned
	 * @throws InvalidCommandException
	 */
	public abstract double left(Command degrees) throws InvalidCommandException;

	/**
	 * Sets the turtle's heading to the value that executing degrees returns.
	 * @param degrees
	 * @return the degrees turned from the turtle's heading to the new heading
	 * @throws InvalidCommandException
	 */
	public abstract double setHeading(Command degrees) throws InvalidCommandException;

	/**
	 * Points the turtle's heading towards the point (a, b) where a and b are the value that
	 * executing x and y return, respectively.
	 * @param x
	 * @param y
	 * @return the degrees turned from the turtle's heading to the new heading
	 * @throws InvalidCommandException
	 */
	public abstract double towards(Command x, Command y) throws InvalidCommandException;

	/**
	 * Sets the turtle's location to the point (a, b) where a and b are the value that
	 * executing x and y return, respectively.
	 * @param x
	 * @param y
	 * @return the distance traveled
	 * @throws InvalidCommandException
	 */
	public abstract double goTo(Command x, Command y) throws InvalidCommandException;

	/**
	 * Sets the turtle's location to the point (d, e)
	 * @param d
	 * @param e
	 * @return the distance traveled.
	 */
	public abstract double goTo(double d, double e);

	/**
	 * Gets the turtle's current heading
	 * @return the turtle's current heading in degrees
	 */
	public abstract double getHeading();

	/**
	 * Gets the turtle's x coordinate
	 * @return the turtle's current x coordinate
	 */
	public abstract double getX();

	/**
	 * Gets the turtle's y coordinate
	 * @return the turtle's current y coordinate
	 */
	public abstract double getY();
	
	/**
	 * Turns the turtle so that its heading is radians.
	 * @param radians
	 * @return the degrees turned
	 */
	abstract public double setHeading(double radians);

	/**
	 * Sets the turtles pen to be down if b, or up if not b
	 * @param (boolean) b
	 * @return 1 if b, 0 if not b
	 */
	abstract public double setPen(boolean b);

	/**
	 * Checks if the turtle is drawing
	 * @return true iff the turtle's pen is down.
	 */
	abstract public boolean getPenDown();

	/**
	 * Gets the turtle's pen color index
	 * @return the index of the color in the turtle's pen
	 */
	abstract public double getPenColor();

	/**
	 * Set the turtle's pen color to the color with index index
	 * @param index
	 * @return index
	 */
	abstract public double setPenColor(int index);

	/**
	 * Gets the turtle's current location
	 * @return (Point) the turtle's current location
	 */
	abstract public Point getCurrentLocation();

	/**
	 * Gets the turtle's location before it moved the last time
	 * @return (Point) the turtle's previous location
	 */
	abstract public Point getPreviousLocation();

	/**
	 * Gets a TurtleView representation of the turtle, which can be passed to the View
	 * @return (TurtleView) this
	 */
	abstract public TurtleView toTurtleView();

	/**
	 * Set the turtle's visibility
	 * @param (boolean) b the turtle's new visibility
	 * @return 1 if b, 0 if not b
	 */
	abstract public double setVisibility(boolean b);

	/**
	 * Gets the turtle's visibility
	 * @return 1 if visible, 0 if not visible
	 */
	abstract public boolean getVisibility();

	/**
	 * Sets the turtle's pen width
	 * @param (double) size the pens new width
	 * @return size
	 */
	abstract public double setPenSize(double size);

	/**
	 * Gets the turtle's pen width
	 * @return (double) the pen's current width
	 */
	abstract public int getPenSize();

	/**
	 * Gets the turtle's shape index
	 * @return index
	 */
	abstract public int getShape();

	/**
	 * Sets the turtle's shape index to index
	 * @param index
	 * @return index
	 */
	abstract public double setShape(int index);

	/**
	 * Sets the turtle's pen size to the value returned by executing the command size
	 * @param size
	 * @return the value of size
	 * @throws InvalidCommandException
	 */
	public abstract double setPenSize(Command size) throws InvalidCommandException;

	/**
	 * Sets the turtle's shape index to the value returned by executing the command shape
	 * @param shape
	 * @return the value of shape
	 * @throws InvalidCommandException
	 */
	public abstract double setShape(Command shape) throws InvalidCommandException;

	/**
	 * Sets the turtle's pn color to the color of the index returned by executing the command index
	 * @param index
	 * @return the value of index
	 * @throws InvalidCommandException
	 */
	public abstract double setPenColor(Command index) throws InvalidCommandException;

	/**
	 * 
	 * @return the index of the turtle in it's TurtleContainer.
	 */
	public abstract int getIndex();



	
}
