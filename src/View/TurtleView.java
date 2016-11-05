package View;

import Model.Point;

/**
 * This interface gives the view access to everything it needs to know about a Turtle to draw the Turtle.
 * @author Weston
 *
 */
public interface TurtleView {

	/**
	 *  Gets the turtle's current location.
	 * @return (Point) the Turtle's current x, y coordinates.
	 */
	abstract public Point getCurrentLocation();
	
	/**
	 *  Gets the turtle's current heading.
	 * @return (double) the Turtle's current heading in degrees.
	 */
	abstract public double getCurrentHeading();
	
	/**
	 *  Gets the turtle's last location.
	 * @return (Point) the Turtle's last x, y coordinates.
	 */
	abstract public Point getPreviousLocation();
	
	/**
	 *  Gets the turtle's last heading.
	 * @return (double) the Turtle's last heading in degrees.
	 */
	abstract public double getPreviousHeading();
	
	/**
	 * Gets the Turtle's filepath to it's image representation.
	 * @return (String) Filepath to the image used to represent the Turtle.
	 */
	abstract public String getImagePath();
	
	/**
	 * Sets the Turtle's filepath to it's image representation.
	 * @param image Filepath to turtle's new image.
	 */
	abstract public void setTurtleImage(String image);
	
	/**
	 * Checks if the turtle's pen is down (drawing).
	 * @return true if the Turtle's pen is down, false otherwise.
	 */
	abstract public boolean getPenDown();
	
}
