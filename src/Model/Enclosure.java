package Model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Enclosure {
	List<Turtle> myTurtles;
	List<Line> myLines;
	
	int myActiveTurtle;
	
	double myMaximumX;
	double myMaximumY;
	
	public Enclosure(double maxX, double maxY){
		myMaximumX = maxX;
		myMaximumY = maxY;
		clearScreen();
	}
	
	public void clearScreen(){
		myLines = new ArrayList<Line>();
		myTurtles = new ArrayList<Turtle>();
		
		myTurtles.add(new Turtle());
		myActiveTurtle = 0;
	}
	
	public double left(double degrees){
		Turtle t = getActiveTurtle();
		
		t.setHeading(t.getHeading() + Math.toRadians(degrees));
		return degrees;
	}
	
	public double right(double degrees){
		return left(-degrees);
	}
	
	public double setHeading(double degrees){
		return getActiveTurtle().setHeading(Math.toRadians(degrees));

	}
	
	public double towards(double x, double y){
		throw new UnsupportedOperationException();
	}
	
	public double forward(double dist){
		double result = dist;
		
		while (dist > 0){
			double toEdge = distToEdge(myActiveTurtle);
			
			if (dist > toEdge){
				forwardNoEdgeChecking(toEdge);
				wrapTurtle(myActiveTurtle);
				dist -= toEdge; 
			}
		}

		return result;
	}
	
	public double back(double dist){
		left(180);
		double result = forward(dist);
		right(180);
		return result;
	}
	
	public double goTo(double x, double y){
		Turtle t = getActiveTurtle();
		double dist = Math.sqrt(Math.pow(t.getX() - x, 2) + Math.pow(t.getY() - y, 2));
		
		towards(x, y);
		return forward(dist);
	}
	
	private Turtle getActiveTurtle(){
		return myTurtles.get(myActiveTurtle);
	}
	
	private void wrapTurtle(int turtleNumber){
		double newX = myTurtles.get(turtleNumber).getX();
		double newY = myTurtles.get(turtleNumber).getY();
		
		if (Math.abs(newX) >= myMaximumX)
			newX *= -1;
		if (Math.abs(newY) >= myMaximumY)
			newY *= -1;
	}
	
	private void forwardNoEdgeChecking(double dist){
		Turtle t = getActiveTurtle();
		Point start = new Point(t.getLocation());
		
		double newX = t.getX() + dist * Math.cos(t.getHeading());
		double newY = t.getY() + dist * Math.sin(t.getHeading());
		
		newX = newX % myMaximumX;
		newY = newY % myMaximumY;
		
		t.setLocation(newX, newY);
		
		if (t.isDrawing())
			myLines.add(new Line(start, t.getLocation(), t.getPenColor()));
	}
	
	/**
	 * @param turtleNumber The index of the turtle to check the distance for.
	 * @return distance the turtle needs to travel along it's heading to hit an edge
	 */
	private double distToEdge(int turtleNumber){
		Turtle t = myTurtles.get(turtleNumber);
		double heading = t.getHeading();
		
		double xDist = Double.POSITIVE_INFINITY;
		double yDist = Double.POSITIVE_INFINITY;
		
		if ((heading < 90 && heading >= 0) || (heading > 270 && heading <= 360))
			xDist = myMaximumX - t.getX();
		if (heading > 180 && heading < 360)
			xDist = t.getX() - myMaximumX;
		
		if (heading > 0 || heading < 180)
			yDist = myMaximumY - t.getY();
		if (heading > 180 && heading < 360)
			yDist = t.getY() - myMaximumY;
		
		xDist *= Math.cos(heading);
		yDist *= Math.sin(heading);
		
		return Math.min(xDist, yDist);
	}

	

}
