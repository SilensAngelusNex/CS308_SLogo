package Model;

import java.awt.Color;
import java.awt.Point;

import View.TurtleView;

public interface TurtleModel {
	
	abstract public double getHeading();
	
	abstract public double setHeading(double radians);
	
	abstract public double getX();
	
	abstract public double getY();
	
	abstract public void setLocation(double newX, double newY);
	
	abstract public boolean isDrawing();
	
	abstract public Color getPenColor();
	
	abstract public Point getCurrentLocation();
	
	abstract public Point getPreviousLocation();
	
	abstract public TurtleView toTurtleView();
	
}
