package Model;

import Model.Commands.Command;
import javafx.util.Pair;
import parser.InvalidCommandException;

/**
 * The Command objects use this interface to manipulate the SlogoModel.
 * @author Weston
 *
 */
public interface CommandableModel {
	
	//Turtle Cammands
	/**
	 * Deletes all lines, all but one turtle, and moves the remaining turtle to the middle of the screen.
	 * @return the distance the active turtle moved
	 */
	public double clearScreen();
	
	//Variable Commands
	public double set(String name, double val);
	public double get(String name);

	//Display Commands
	public double setBackground(double index);
	public double setPallet(double index, double r, double g, double b);

	//Multiturtle Commands
	public double turtleNumber();
	public Pair<Double, TurtleModel> newCompositeTurtleCondition(Command cond) throws InvalidCommandException;
	public Pair<Double, TurtleModel> newCompositeTurtle(Command list) throws InvalidCommandException;
	public void setActiveTurtle(TurtleModel t);
}

