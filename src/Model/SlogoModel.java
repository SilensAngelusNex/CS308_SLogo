package Model;

import Controller.ModelInViewInterface;
import parser.ExpressionTree;
import parser.MainParser;
import parser.ParserUtils;

public class SlogoModel implements ModelInViewInterface{
	private Calculator myCalculator;
	private Enclosure myTurtleEnclosure;
	private VariableContainer myVariables;
	private MainParser myParser;
	private TreeExecutor myExecutor;
	
	public SlogoModel(EnclosureObserver e, double enclosureMaxX, double enclosureMaxY){
		myTurtleEnclosure = new Enclosure(enclosureMaxX, enclosureMaxY);
		myTurtleEnclosure.addListener(e);
		
		myCalculator = new Calculator();
		myVariables = new VariableContainer();
		myParser = new MainParser(ParserUtils.ENGLISH_FILE_PATH);
		myExecutor = new TreeExecutor(ParserUtils.ENGLISH_FILE_PATH, ParserUtils.SYNTAX_FILE_PATH);
	}
	
	public void setTurtleImage(String image){
		myTurtleEnclosure.setTurtleImage(image);
	}
	
	public String parseAndExecute(String command){
		ExpressionTree toExec = myParser.getExpressionTreeFromCommand(command);
		double[] result = myExecutor.exec(toExec, this);
		
		StringBuilder s = new StringBuilder();
		for (double d : result) { 
			s.append(d).append(" ");
		}
		s.append("\n");
		return s.toString();
	}
	//Turtle Cammands
	public double forward(double distance){
		return myTurtleEnclosure.forward(distance);
	}
	public double back(double distance){
		return myTurtleEnclosure.back(distance);
	}
	public double left(double degrees){
		return myTurtleEnclosure.left(degrees);
	}
	public double right(double degrees){
		return myTurtleEnclosure.right(degrees);
	}
	public double setHeading(double degrees){
		return myTurtleEnclosure.setHeading(degrees);
	}
	public double towards(double x, double y){
		return myTurtleEnclosure.towards(x, y);
	}
	public double goTo(double x, double y){
		return myTurtleEnclosure.goTo(x, y);
	}
	public double penDown(){
		return myTurtleEnclosure.penDown();
	}
	public double penUp(){
		return myTurtleEnclosure.penUp();
	}
	public double showTurtle(){
		return myTurtleEnclosure.showTurtle();
	}
	public double hideTurtle(){
		return myTurtleEnclosure.hideTurtle();
	}
	public double home(){
		return myTurtleEnclosure.home();
	}
	public double clearScreen(){
		return myTurtleEnclosure.clearScreen();
	}
	
	//Turtle Queries
	public double xCor(){
		return myTurtleEnclosure.xCor();
	}
	public double yCor(){
		return myTurtleEnclosure.yCor();
	}
	public double heading(){
		return myTurtleEnclosure.heading();
	}
	public double isPenDown(){
		return myTurtleEnclosure.isPenDown();
	}
	public double isShowing(){
		return myTurtleEnclosure.isShowing();
	}
	
	//Math Commands
	public double sum(double a, double b){
		return myCalculator.sum(a, b);
	}
	public double difference(double a, double b){
		return myCalculator.difference(a, b);
	}
	public double product(double a, double b){
		return myCalculator.product(a, b);
	}
	public double quotient(double a, double b){
		return myCalculator.quotient(a, b);
	}
	public double remainder(double a, double b){
		return myCalculator.remainder(a, b);
	}
	public double minus(double a){
		return myCalculator.minus(a);
	}
	public double random(double max){
		return myCalculator.random(max);
	}
	public double sin(double degrees){
		return myCalculator.sin(degrees);
	}
	public double cos(double degrees){
		return myCalculator.cos(degrees);
	}
	public double tan(double degrees){
		return myCalculator.tan(degrees);
	}
	public double atan(double degrees){
		return myCalculator.atan(degrees);
	}
	public double log(double a){
		return myCalculator.log(a);
	}
	public double pow(double a, double b){
		return myCalculator.pow(a, b);
	}
	public double pi(){
		return myCalculator.pi();
	}
	
	//Boolean Commands
	public double less(double a, double b){
		return myCalculator.less(a, b);
	}
	public double greater(double a, double b){
		return myCalculator.greater(a, b);
	}
	public double equal(double a, double b){
		return myCalculator.equal(a, b);
	}
	public double notEqual(double a, double b){
		return myCalculator.notEqual(a, b);
	}
	public double and(double a, double b){
		return myCalculator.and(a, b);
	}
	public double or(double a, double b){
		return myCalculator.or(a, b);
	}
	public double not(double a){
		return myCalculator.not(a);
	}
	
	//Variable Commands
	public double set(String name, double val){
		return myVariables.set(name, val);
	}
	public double get(String name){
		return myVariables.get(name);
	}
}