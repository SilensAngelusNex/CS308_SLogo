package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Commands.Command;
import javafx.util.Pair;
import parser.InvalidCommandException;


public class Enclosure implements Observable<EnclosureObserver>, TurtleContainer{
	private List<EnclosureObserver> myObservers;
	private Map<Integer, TurtleModel> myTurtles;
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
		myTurtles = new HashMap<Integer, TurtleModel>();

		myActiveTurtle = new Turtle(myColors, this);
		addTurtle(0, myActiveTurtle);
	}
	
	public double clearScreen(){
		removeAllLines();
		removeAllTurtles();
		
		addTurtle(0, myActiveTurtle);
		myActiveTurtle.setHeading(Math.toRadians(90));
		
		return myActiveTurtle.goTo(0, 0);

	}


	@Override
	public void addListener(EnclosureObserver v) {
		myObservers.add(v);
		
		for (TurtleModel t: myTurtles.values()){
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
	
	private void addTurtle(int index, TurtleModel t){
		myTurtles.put(index, t);
		notifyListenersAddTurtle(t);
	}
	
	private void removeAllTurtles(){
		for (TurtleModel t: myTurtles.values()){
			notifyListenersRemoveTurtle(t);
		}
		myTurtles = new HashMap<Integer, TurtleModel>();
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
		return myActiveTurtle.getIndex();
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
	public int getIndex(TurtleModel turtle) {
		for (Map.Entry<Integer, TurtleModel> e : myTurtles.entrySet())
			if (e.getValue() == turtle)
				return e.getKey();
		return -1;
	}

	@Override
	public void turtleAdd(TurtleModel turtle) {
		notifyListenersAddTurtle(turtle);
	}

	@Override
	public void turtleRemove(TurtleModel turtle) {
		notifyListenersRemoveTurtle(turtle);
	}

	public Pair<Double, TurtleModel> newCompositeTurtle(Command list) throws InvalidCommandException {
		//A bale is a group of turtles. :D
		ArrayList<TurtleModel> bale = new ArrayList<TurtleModel>();
		TurtleModel currTurtle = list.getTurtle();
		
		int index = 0;
		for (Command child: list.getChildren()){
			index = (int) child.execute(currTurtle);
			bale.add(getTurtle(index));
		}
		
		return new Pair<Double, TurtleModel>((double) index, new CompositeTurtle(bale));
		
	}

	private TurtleModel getTurtle(double index) {
		if (!myTurtles.containsKey((int) index)){
			myTurtles.put((int) index, new Turtle(myColors, this));
		}
		return myTurtles.get((int) index);
	}

	public Pair<Double, TurtleModel> newCompositeTurtleCondition(Command cond) throws InvalidCommandException {
		//A bale is a group of turtles. :D
		ArrayList<TurtleModel> bale = new ArrayList<TurtleModel>();
		
		int index = 0;
		for (TurtleModel t: myTurtles.values())
			if (cond.execute(t) != 0)
				bale.add(t);
		
		return new Pair<Double, TurtleModel>((double) index, new CompositeTurtle(bale));
		
	}

	public void setActiveTurtle(TurtleModel t) {
		myActiveTurtle = t;
	}
}
