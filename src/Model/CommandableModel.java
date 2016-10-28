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
	
	//Math Commands
	public double sum(double a, double b);
	public double difference(double a, double b);
	public double product(double a, double b);
	public double quotient(double a, double b);
	public double remainder(double a, double b);
	public double minus(double a);
	public double random(double max);
	public double sin(double degrees);
	public double cos(double degrees);
	public double tan(double degrees);
	public double atan(double degrees);
	public double log(double a);
	public double pow(double a, double b);
	public double pi();
	
	//Boolean Commands
	public double less(double a, double b);
	public double greater(double a, double b);
	public double equal(double a, double b);
	public double notEqual(double a, double b);
	public double and(double a, double b);
	public double or(double a, double b);
	public double not(double a);
	
	//Variable Commands
	public double set(String name, double val);
	public double get(String name);

}

