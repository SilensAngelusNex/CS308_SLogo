package Model;

import java.util.ArrayList;
import java.util.List;


public class Enclosure implements Observable<EnclosureObserver>, TurtleContainer{
	private List<EnclosureObserver> myObservers;
	private List<TurtleModel> myTurtles;
	private List<LineModel> myLines;
	private TurtleModel myActiveTurtle;
	private ColorPallet myColors;
	
	private double myMaximumX;
	private double myMaximumY;
	
	public Enclosure(double maxX, double maxY, ColorPallet colors){
		myColors = colors;
		myMaximumX = maxX;
		myMaximumY = maxY;
		myObservers = new ArrayList<EnclosureObserver>();
		myLines = new ArrayList<LineModel>();
		myTurtles = new ArrayList<TurtleModel>();

		myActiveTurtle = new Turtle(myColors, this);
		addTurtle(myActiveTurtle);
	}
	
	public double clearScreen(){
		removeAllLines();
		removeAllTurtles();
		
		addTurtle(myActiveTurtle);
		myActiveTurtle.setHeading(Math.toRadians(90));
		
		return myActiveTurtle.goTo(0, 0);

	}


	@Override
	public void addListener(EnclosureObserver v) {
		myObservers.add(v);
		
		for (TurtleModel t: myTurtles){
			if (t.getVisibility())
				v.addTurtle(t.toTurtleView());
		}
		
		for (LineModel l: myLines)
				v.addLine(l);
	}


	@Override
	public void removeListener(EnclosureObserver v) {
		myObservers.remove(v);
	}
	
	private void addTurtle(TurtleModel t){
		myTurtles.add(t);
		notifyListenersAddTurtle(t);
	}
	
	private void removeAllTurtles(){
		for (TurtleModel t: myTurtles){
			notifyListenersRemoveTurtle(t);
		}
		myTurtles = new ArrayList<TurtleModel>();
	}
	
	private void removeAllLines(){
		for (LineModel l: myLines){
			notifyListenersRemoveLine(l);
		}
		myLines = new ArrayList<LineModel>();
	}

	
	private void notifyListenersAddTurtle(TurtleModel t){
		for (EnclosureObserver e: myObservers){
			e.addTurtle(t.toTurtleView());
		}
	}
	private void notifyListenersRemoveTurtle(TurtleModel t){
		for (EnclosureObserver e: myObservers){
			e.removeTurtle(t.toTurtleView());
		}
	}
	private void notifyListenersMoveTurtle(TurtleModel t){
		for (EnclosureObserver e: myObservers){
			e.moveTurtle(t.toTurtleView());
		}
	}
	private void notifyListenersAddLine(LineModel l){
		for (EnclosureObserver e: myObservers){
			e.addLine(l);
		}
	}
	private void notifyListenersRemoveLine(LineModel l){
		for (EnclosureObserver e: myObservers){
			e.removeLine(l);
		}
	}

	public double ActiveID() {
		return myTurtles.indexOf(myActiveTurtle);
	}

	public double turtles() {
		return myTurtles.size();
	}

	public int getShape() {
		return myActiveTurtle.getShape();
	}
	public double setShape(int index) {
		myActiveTurtle.setShape(index);
		return index;
	}

	public TurtleModel getActiveTurtle() {
		return myActiveTurtle;
	}

	@Override
	public void turtleMove(TurtleModel t) {
		notifyListenersMoveTurtle(t);
	}

	@Override
	public double getMaxY() {
		return myMaximumY;
	}

	@Override
	public double getMaxX() {
		return myMaximumX;
	}

	@Override
	public void addLine(LineModel toAdd) {
		myLines.add(toAdd);
		notifyListenersAddLine(toAdd);
	}

	@Override
	public int getIndex(Turtle turtle) {
		return myTurtles.indexOf(turtle);
	}

	@Override
	public void turtleAdd(Turtle turtle) {
		notifyListenersAddTurtle(turtle);
	}

	@Override
	public void turtleRemove(Turtle turtle) {
		notifyListenersRemoveTurtle(turtle);
	}
}
