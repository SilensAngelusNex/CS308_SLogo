package Model;

public interface TurtleContainer {


	public double getMaxY();

	public double getMaxX();

	public void addLine(LineModel toAdd);

	public int getIndex(Turtle turtle);
	
	public void turtleAdd(Turtle turtle);
	
	public void turtleMove(TurtleModel t);

	public void turtleRemove(Turtle turtle);
}
