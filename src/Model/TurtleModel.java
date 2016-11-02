package Model;

import parser.InvalidCommandException;
import Model.Commands.Command;
import View.TurtleView;

public interface TurtleModel {
	
	public abstract double forward(Command distance) throws InvalidCommandException;

	public abstract double back(Command distance) throws InvalidCommandException;

	public abstract double right(Command degrees) throws InvalidCommandException;

	public abstract double left(Command degrees) throws InvalidCommandException;

	public abstract double setHeading(Command degrees) throws InvalidCommandException;

	public abstract double towards(Command x, Command y) throws InvalidCommandException;

	public abstract double goTo(Command x, Command y) throws InvalidCommandException;

	public abstract double goTo(double d, double e);

	public abstract double getHeading();

	public abstract double getX();

	public abstract double getY();
	
	abstract public double setHeading(double radians);

	abstract public boolean isDrawing();

	abstract public double setPen(boolean b);

	abstract public boolean getPenDown();

	abstract public double getPenColor();

	abstract public double setPenColor(int index);

	abstract public Point getCurrentLocation();

	abstract public Point getPreviousLocation();

	abstract public TurtleView toTurtleView();

	abstract public double setVisibility(boolean b);

	abstract public boolean getVisibility();

	abstract public double setPenSize(double size);

	abstract public int getPenSize();

	abstract public int getShape();

	abstract public double setShape(int index);

	public abstract double setPenSize(Command size) throws InvalidCommandException;

	public abstract double setShape(Command shape) throws InvalidCommandException;

	public abstract double setPenColor(Command index) throws InvalidCommandException;

	public abstract int getIndex();



	
}
