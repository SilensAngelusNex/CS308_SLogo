package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Commands.Command;
import View.TurtleView;
import parser.InvalidCommandException;

public class CompositeTurtle implements TurtleModel{
	List<TurtleModel> myTurtles;
	

	public CompositeTurtle(ArrayList<TurtleModel> bale) {
		myTurtles = bale;
	}

	@Override
	public double forward(Command distance) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.forward(distance);
		return result;
	}

	@Override
	public double back(Command distance) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.back(distance);
		return result;
	}

	@Override
	public double right(Command degrees) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.right(degrees);
		return result;
	}

	@Override
	public double left(Command degrees) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.left(degrees);
		return result;
	}

	@Override
	public double setHeading(Command degrees) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setHeading(degrees);
		return result;
	}

	@Override
	public double towards(Command x, Command y) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.towards(x, y);
		return result;
	}

	@Override
	public double goTo(Command x, Command y) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.goTo(x, y);
		return result;
	}

	@Override
	public double goTo(double d, double e) {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.goTo(d, e);
		return result;
	}

	@Override
	public double getHeading() {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getHeading();
		return result;
	}

	@Override
	public double getX() {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getX();
		return result;
	}

	@Override
	public double getY() {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getY();
		return result;
	}

	@Override
	public double setHeading(double radians) {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setHeading(radians);
		return result;
	}

	@Override
	public boolean isDrawing() {
		boolean result = false;
		for (TurtleModel t: myTurtles)
			result = result && t.isDrawing();
		return result;
	}

	@Override
	public double setPen(boolean b) {
		for (TurtleModel t: myTurtles)
			t.setPen(b);
		return b ? 1 : 0;
	}

	@Override
	public boolean getPenDown() {
		return isDrawing();
	}

	@Override
	public double getPenColor() {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getPenColor();
		return result;
	}

	@Override
	public double setPenColor(int index) {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setPenColor(index);
		return result;
	}

	@Override
	public Point getCurrentLocation() {
		double xResult = 0;
		double yResult = 0;
		for (TurtleModel t: myTurtles){
			xResult += t.getX();
			yResult += t.getY();
		}
		return new Point (xResult, yResult);
	}

	@Override
	public Point getPreviousLocation() {
		double xResult = 0;
		double yResult = 0;
		for (TurtleModel t: myTurtles){
			xResult += t.getPreviousLocation().getX();
			yResult += t.getPreviousLocation().getY();
		}
		return new Point (xResult, yResult);
	}

	@Override
	public TurtleView toTurtleView() {
		return myTurtles.get(myTurtles.size() - 1).toTurtleView();
	}

	@Override
	public double setVisibility(boolean b) {
		for (TurtleModel t: myTurtles)
			t.setVisibility(b);
		return b ? 1 : 0;
	}

	@Override
	public boolean getVisibility() {
		boolean result = false;
		for (TurtleModel t: myTurtles)
			result = result && t.isDrawing();
		return result;
	}

	@Override
	public double setPenSize(double size) {
		for (TurtleModel t: myTurtles)
			t.setPenSize(size);
		return size;
	}

	@Override
	public int getPenSize() {
		int result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getPenSize();
		return result;
	}

	@Override
	public int getShape() {
		int result = 0;
		for (TurtleModel t: myTurtles)
			result = t.getShape();
		return result;
	}

	@Override
	public double setShape(int index) {
		for (TurtleModel t: myTurtles)
			t.setShape(index);
		return index;
	}

	@Override
	public double setPenSize(Command size) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setPenSize(size);
		return result;
	}

	@Override
	public double setShape(Command shape) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setPenSize(shape);
		return result;
	}

	@Override
	public double setPenColor(Command index) throws InvalidCommandException {
		double result = 0;
		for (TurtleModel t: myTurtles)
			result = t.setPenColor(index);
		return result;
	}

	@Override
	public int getIndex() {
		return -1;
	}
	

}
