package Model;

/**
 * An interface to allow the turtles to interact with the space they live in.
 * @author Weston
 *
 */
public interface TurtleContainer {


	public double getMaxY();

	public double getMaxX();

	public void addLine(LineModel toAdd);

	public int getIndex(TurtleModel turtle);
	
	public void turtleAdd(TurtleModel turtle);
	
	public void turtleMove(TurtleModel t);

	public void turtleRemove(TurtleModel turtle);
}
