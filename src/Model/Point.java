package Model;

/**
 * A class used to contain pairs of doubles, used to represent euclidean coordinates.
 * @author Weston
 *
 */
public class Point {
	private double myX;
	private double myY;
	
	public Point(Number x, Number y){
		myX = x.doubleValue();
		myY = y.doubleValue();
	}
	
	/**
	 * Gets the point's x value
	 * @return (double) x coordinate
	 */
	public double getX(){
		return myX;
	}
	
	/**
	 * Gets the point's y value
	 * @return (double) y coordinate
	 */
	public double getY(){
		return myY;
	}
	
	/**
	 * Sets the point's location to be (x, y)
	 * @param x
	 * @param y
	 */
	public void setLocation(Number x, Number y){
		myX = x.doubleValue();
		myY = y.doubleValue();
	}
	
	/**
	 * Find the euclidean distance between two points
	 * @param p
	 * @return the euclidean distance to point p
	 */
	public double euclideanDistance(Point p){
		return Math.sqrt(
				Math.pow(myX - p.myX, 2) +
				Math.pow(myY - p.myY, 2)
				);
	}
	
	/**
	 * Find the heading to a point
	 * @param p
	 * @return the heading to point p from this point
	 */
	public double headingTo(Point p){
		return Math.atan((p.myY - myY) / (p.myX - myX));
	}
}
