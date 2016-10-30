package Model;

import java.awt.Color;

import View.TurtleView;

public interface TurtleModel {
	
	abstract public double getHeading();
	
	abstract public double setHeading(double radians);
	
	abstract public double getX();
	
	abstract public double getY();
	
	abstract public void setLocation(double newX, double newY);
	
	abstract public boolean isDrawing();
	
	abstract public void setPen(boolean b);
	
	abstract public boolean getPenDown();
	
	abstract public Color getPenColor();
	
	abstract public double setPenColor(Color c);
	
	abstract public Point getCurrentLocation();
	
	abstract public Point getPreviousLocation();
	
	abstract public TurtleView toTurtleView();

	abstract public void setVisibility(boolean b);
	
	abstract public boolean getVisibility();

	abstract public double setPenSize(double size);

	abstract public int getPenSize();
	
	abstract public int getShape();
	
	abstract public void setShape(int index);
	
}
