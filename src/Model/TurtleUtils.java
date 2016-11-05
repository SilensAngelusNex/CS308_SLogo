package Model;

/**
 * A class to store turtle utilities, namely the strings corresponding to their shape index values.
 * @author Weston
 *
 */
public class TurtleUtils {
	public static final String[] SHAPES = new String[] {"Triangle", "Square", "Hexagon", "Circle"};
	
	/**
	 * Gets a shape from a shape index
	 * @param index
	 * @return the shape corresponding to index
	 */
	public String getShape(int index) {
		return SHAPES[index];
	}

}
