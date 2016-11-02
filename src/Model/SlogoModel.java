package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Controller.ModelInViewInterface;
import Model.Commands.Command;
import parser.FileHandler;
import parser.InvalidCommandException;
import parser.Language;
import parser.MainParser;

public class SlogoModel implements ModelInViewInterface, Observable<VariableObserver>, CommandableModel{
	private Enclosure myTurtleEnclosure;
	private VariableContainer myVariables;
	private MainParser myParser;
	private ColorPallet myColors;
	private List<VariableObserver> myObservers;
	private FileHandler myFileHandler;
	
	public SlogoModel(EnclosureObserver e, ColorObserver c, double enclosureMaxX, double enclosureMaxY){
		myTurtleEnclosure = new Enclosure(enclosureMaxX, enclosureMaxY, myColors);
		myTurtleEnclosure.addListener(e);
		
		myVariables = new VariableContainer();
		myParser = new MainParser(Language.ENGLISH, this);
		myObservers = new ArrayList<VariableObserver>();
		myColors = new ColorPallet();
		myColors.addListener(c);
	}
	
	public void setTurtleImage(String image){
		myTurtleEnclosure.setTurtleImage(image);
	}
	
	/**
	 * @throws InvalidCommandException 
	 * 
	 */
	public String parseAndExecute(String command) throws InvalidCommandException{
		Command toExec = myParser.getExpressionTreeFromCommand(command);
		toExec.execNonTurtle(myTurtleEnclosure.getActiveTurtle());
		double result = toExec.execute(myTurtleEnclosure.getActiveTurtle());
		
		return Double.toString(result);
	}
	
<<<<<<< src/Model/SlogoModel.java
	//Turtle Commands
=======
	@Override
	public void changeLanguage(String language) {
		for (Language l : Language.values()) {
			if (l.getLangName().equals(language)) {
				myParser = new MainParser(l, this);
				return;
			}
		}
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
>>>>>>> src/Model/SlogoModel.java
	public double clearScreen(){
		return myTurtleEnclosure.clearScreen();
	}
	
	//Variable Commands
	public double set(String name, double val) {
		double result;
		if (myVariables.has(name)){
			result = myVariables.set(name, val);
//			notifyListenersChangeVariable(name, val);
		} else {
			result = myVariables.set(name, val);
			System.out.println("setting a variable");
			notifyListenersAddVariable(name, val);
		}
		return result;
	}
	public double remove(String name) {
		double result = 0;
		if (myVariables.has(name)) {
			myVariables.remove(name);
			notifyListenersRemoveVariable(name);
			result = 1;
		}
		return result;
	}
	
	
	//Display Commands
	public double setBackground(double index){
		return myColors.setBackground((int) index);
	}
	public double setPallet(double index, double r, double g, double b){
		return myColors.setIndex((int) index, (int) r, (int) g, (int) b);
	}
	
	//Multiturtle Commands
	public double ID() {
		return myTurtleEnclosure.ActiveID();
	}
	public double turtles() {
		return myTurtleEnclosure.turtles();
	}
	
//	private void notifyListenersChangeVariable(String name, double value) {
//		for (VariableObserver v: myObservers)
//			v.changeVariable(name, value);
//	}
	private void notifyListenersAddVariable(String name, double value) {
		for (VariableObserver v: myObservers){
			v.addVariable(name, value);
			System.out.println("notifying listeners");
		}
			
	}
	private void notifyListenersRemoveVariable(String name) {
		for (VariableObserver v: myObservers)
			v.deleteVariable(name);
	}

	public double get(String name){
		return myVariables.get(name);
	}

	@Override
	public void addListener(VariableObserver v) {
		myObservers.add(v);	
	}

	@Override
	public void removeListener(VariableObserver v) {
		myObservers.remove(v);
	}
<<<<<<< src/Model/SlogoModel.java

	@Override
	public void setTurtleImage(String image) {
		myTurtleEnclosure.getActiveTurtle().toTurtleView().setTurtleImage(image);
	}

	@Override
	public double turtleNumber() {
		return myTurtleEnclosure.turtles();
	}

}
=======
	
	public void addCommandListener(CommandObserver o) {
		myParser.getCommandFactory().addListener(o);
	}
	
	public FileHandler getFileHandler() {
		return myFileHandler;
	}
}
>>>>>>> src/Model/SlogoModel.java
