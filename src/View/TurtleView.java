package View;

import Model.Point;

public interface TurtleView {

	abstract public Point getCurrentLocation();
	
	abstract public double getCurrentHeading();
	
	abstract public Point getPreviousLocation();
	
	abstract public double getPreviousHeading();
	
	abstract public String getImagePath();
	
	abstract public void setTurtleImage(String image);
	
}
