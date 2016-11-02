package Model;

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
}

