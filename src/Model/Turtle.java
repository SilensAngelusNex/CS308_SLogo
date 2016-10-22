package Model;

import java.awt.Color;
import java.awt.Point;

import View.TurtleView;

public class Turtle implements TurtleModel, TurtleView{
	Point myPosition;
	Point myLastPosition;
	
	double myHeading;
	double myLastHeading;
	
	Color myPenColor;
	boolean myPenDown;
	
	public Turtle(){
		myPosition = new Point(0, 0);
		myHeading = Math.toRadians(90);
		myPenDown = true;
		myPenColor = new Color(255, 255, 255);
	}
	
	@Override
	public double getHeading(){
		return myHeading;
	}
	
	@Override
	public double setHeading(double radians){
		double result = radians - myHeading ;
		
		radians = radians % (2 * Math.PI);

		if (radians < 0)
			radians *= -1;
		
		myHeading = radians;
		
		return result;
	}
	@Override
	public double getX(){
		return myPosition.getX();
	}
	@Override
	public double getY(){
		return myPosition.getY();
	}

	@Override
	public boolean isDrawing() {
		return myPenDown;
	}

	@Override
	public void setLocation(double newX, double newY) {
		myPosition.setLocation(newX, newY);
	}

	@Override
	public Color getPenColor() {
		return myPenColor;
	}


	@Override
	public Point getCurrentLocation() {
		return myPosition;
	}


	@Override
	public double getCurrentHeading() {
		return myHeading;
	}


	@Override
	public Point getPreviousLocation() {
		return myLastPosition;
	}


	@Override
	public double getPreviousHeading() {
		return myLastHeading;
	}


	@Override
	public String getImagePath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TurtleView toTurtleView(){
		return this;
	}

}
