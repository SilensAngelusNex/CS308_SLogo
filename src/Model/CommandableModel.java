package Model;

public interface CommandableModel {
	
	//Turtle Cammands
	public double forward(double distance);
	public double back(double distance);
	public double left(double degrees);
	public double right(double degrees);
	public double setHeading(double degrees);
	public double towards(double x, double y);
	public double goTo(double x, double y);
	public double penDown();
	public double penUp();
	public double showTurtle();
	public double hideTurtle();
	public double home();
	public double clearScreen();
	
	//Turtle Queries
	public double xCor();
	public double yCor();
	public double heading();
	public double isPenDown();
	public double isShowing();
	
	//Variable Commands
	public double set(String name, double val);
	public double get(String name);

}

