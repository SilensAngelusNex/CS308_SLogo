package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.ModelInViewInterface;
import Model.Commands.Command;
import parser.InvalidCommandException;
import parser.Language;
import parser.MainParser;

public class SlogoModel implements ModelInViewInterface, Observable<VariableObserver>, CommandableModel{
	private Enclosure myTurtleEnclosure;
	private VariableContainer myVariables;
	private MainParser myParser;
	private ColorPallet myColors;
	private List<VariableObserver> myObservers;
	
	public SlogoModel(EnclosureObserver e, double enclosureMaxX, double enclosureMaxY){
		myVariables = new VariableContainer();
		myParser = new MainParser(Language.ENGLISH, this);
		myObservers = new ArrayList<VariableObserver>();
		myColors = new ColorPallet();
		
		myTurtleEnclosure = new Enclosure(enclosureMaxX, enclosureMaxY, myColors);
		myTurtleEnclosure.addListener(e);
		
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
	
	//Turtle Commands
	public double clearScreen(){
		return myTurtleEnclosure.clearScreen();
	}
	
	//Variable Commands
	public double set(String name, double val) {
		double result;
		if (myVariables.has(name)){
			result = myVariables.set(name, val);
			notifyListenersChangeVariable(name, val);
		} else {
			result = myVariables.set(name, val);
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
	
	private void notifyListenersChangeVariable(String name, double value) {
		for (VariableObserver v: myObservers)
			v.changeVariable(name, value);
	}
	private void notifyListenersAddVariable(String name, double value) {
		for (VariableObserver v: myObservers){
			v.addVariable(name, value);
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

	@Override
	public void setTurtleImage(String image) {
		myTurtleEnclosure.getActiveTurtle().toTurtleView().setTurtleImage(image);
	}

	@Override
	public double turtleNumber() {
		return myTurtleEnclosure.turtles();
	}

}
