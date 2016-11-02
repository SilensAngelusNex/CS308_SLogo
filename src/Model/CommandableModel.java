package Model;

import Model.Commands.Command;
import javafx.util.Pair;
import parser.InvalidCommandException;

public interface CommandableModel {
	
	//Turtle Cammands
	public double clearScreen();
	
	//Variable Commands
	public double set(String name, double val);
	public double get(String name);

	//Display Commands
	public double setBackground(double index);
	public double setPallet(double index, double r, double g, double b);

	public double turtleNumber();

	public Pair<Double, TurtleModel> newCompositeTurtleCondition(Command child) throws InvalidCommandException;

	public Pair<Double, TurtleModel> newCompositeTurtle(Command child) throws InvalidCommandException;
}

