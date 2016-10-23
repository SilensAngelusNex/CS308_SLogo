package Model;

import View.TurtleView;

public interface EnclosureObserver {

	abstract public void addTurtle(TurtleView t);
	
	abstract public void removeTurtle(TurtleView t);
	
	abstract public void moveTurtle(TurtleView t);
	
	abstract public void addLine(LineModel l);
	
	abstract public void removeLine(LineModel l);
}
