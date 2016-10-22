package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class Enclosure implements Observable{
	List<EnclosureObserver> myObservers;

	List<TurtleModel> myTurtles;
	List<Line> myLines;
	
	int myActiveTurtle;
	
	double myMaximumX;
	double myMaximumY;
	
	public Enclosure(double maxX, double maxY){
		myMaximumX = maxX;
		myMaximumY = maxY;
		myObservers = new ArrayList<EnclosureObserver>();
		myLines = new ArrayList<Line>();
		myTurtles = new ArrayList<TurtleModel>();

		myActiveTurtle = 0;
	}
	
	public double forward(double dist){
		return move(dist, getActiveTurtle().getHeading());
	}
	
	public double back(double dist){
		return move(dist, -getActiveTurtle().getHeading());
	}
	
	public double right(double degrees){
		return -left(-degrees);
	}
	
	public double left(double degrees){
		TurtleModel t = getActiveTurtle();
		
		t.setHeading(t.getHeading() + Math.toRadians(degrees));
		return degrees;
	}
	
	public double setHeading(double degrees){
		return getActiveTurtle().setHeading(Math.toRadians(degrees));
	}
	
	public double towards(double x, double y){
		Point destination = new Point(x, y);
		
		return setHeading(getActiveTurtle().getCurrentLocation().headingTo(destination));
	}
	
	public double goTo(double x, double y){
		TurtleModel t = getActiveTurtle();
		towards(x, y);
		
		t.setLocation(x, y);

		notifyListeners(e -> e.moveTurtle(getActiveTurtle().toTurtleView()));
		return t.getCurrentLocation().euclideanDistance(t.getPreviousLocation());
	}
	
	public double penDown(){
		getActiveTurtle().setPen(true);
		return 1;
	}
	public double penUp(){
		getActiveTurtle().setPen(false);
		return 0;
	}
	public double showTurtle(){
		getActiveTurtle().setVisibility(true);
		notifyListeners(e -> e.moveTurtle(getActiveTurtle().toTurtleView()));

		return 1;
	}
	public double hideTurtle(){
		getActiveTurtle().setVisibility(false);
		notifyListeners(e -> e.moveTurtle(getActiveTurtle().toTurtleView()));
		return 0;
	}
	public double home(){
		return goTo(0, 0);
	}
	
	public double clearScreen(){
		TurtleModel t = getActiveTurtle();
		
		removeAllLines();
		removeAllTurtles();
		
		addTurtle(t);
		myActiveTurtle = 0;
		
		return home();

	}
	
	public double xCor(){
		return getActiveTurtle().getX();
	}
	public double yCor(){
		return getActiveTurtle().getY();
	}
	public double heading(){
		return getActiveTurtle().getHeading();
	}
	public double isPenDown(){
		return getActiveTurtle().getPenDown() ? 1: 0;
	}
	public double isShowing(){
		return getActiveTurtle().getVisibility() ? 1 : 0;
	}
	

	private double move(double dist, double heading){
		double result = dist;
		
		while (dist > 0){
			double toEdge = distToEdge(myActiveTurtle, heading);
			
			if (dist > toEdge){
				moveNoEdgeChecking(toEdge, heading);
				wrapTurtle(myActiveTurtle);
				dist -= toEdge; 
			}
		}

		return result;
	}
	

	

	

	
	private TurtleModel getActiveTurtle(){
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
	
	private void moveNoEdgeChecking(double dist, double heading){
		TurtleModel t = getActiveTurtle();
	
		double newX = t.getX() + dist * Math.cos(heading);
		double newY = t.getY() + dist * Math.sin(heading);
		
		newX = newX % myMaximumX;
		newY = newY % myMaximumY;
		
		t.setLocation(newX, newY);
		
		notifyListeners(e -> e.moveTurtle(t.toTurtleView()));
		
		if (t.isDrawing()){
			Line toAdd = new Line(t.getPreviousLocation(), t.getCurrentLocation(), t.getPenColor());
			myLines.add(toAdd);
			notifyListeners(e -> e.addLine(toAdd));
		}
		
	}
	
	/**
	 * @param turtleNumber The index of the turtle to check the distance for.
	 * @return distance the turtle needs to travel along it's heading to hit an edge
	 */
	private double distToEdge(int turtleNumber, double heading){
		TurtleModel t = myTurtles.get(turtleNumber);
		
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

	@Override
	public void addListener(EnclosureObserver v) {
		myObservers.add(v);
	}


	@Override
	public void removeListener(EnclosureObserver v) {
		myObservers.remove(v);
	}
	
	private void addTurtle(Turtle t){
		myTurtles.add(t);
		notifyListeners(e -> e.addTurtle(t.toTurtleView()));
	}
	
	private void addTurtle(TurtleModel t){
		myTurtles.add(t);
		notifyListeners(e -> e.addTurtle(t.toTurtleView()));
	}
	
	private void removeAllTurtles(){
		for (TurtleModel t: myTurtles){
			notifyListeners(e -> e.removeTurtle(t.toTurtleView()));
		}
		myTurtles = new ArrayList<TurtleModel>();
	}
	
	private void removeAllLines(){
		for (Line l: myLines){
			notifyListeners(e -> e.removeLine(l));
		}
		myLines = new ArrayList<Line>();
	}
	private void notifyListeners(Consumer<EnclosureObserver> m){
		for (EnclosureObserver e: myObservers){
			m.accept(e);
		}
	}
}