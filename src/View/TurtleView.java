package View;

import Model.Point;

public interface TurtleView {

	abstract public Point getCurrentLocation();
	
	abstract public double getCurrentHeading();
	
	abstract public Point getPreviousLocation();
	
	abstract public double getPreviousHeading();
	
	abstract public String getImagePath();
	
	/**
	 * @deprecated
	 * @return true
	 * Model is taking care of this now.
	 */
	abstract public boolean isVisible();
}
