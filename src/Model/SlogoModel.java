package Model;

import java.util.ArrayList;
import java.util.List;

import Controller.ModelInViewInterface;
import Model.Commands.Command;
import javafx.util.Pair;
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
	
	public SlogoModel(EnclosureObserver e, List<ColorObserver> c, double enclosureMaxX, double enclosureMaxY){
		myColors = new ColorPallet();
		myTurtleEnclosure = new Enclosure(enclosureMaxX, enclosureMaxY, myColors);
		myTurtleEnclosure.addListener(e);
		
		myVariables = new VariableContainer();
		myParser = new MainParser(Language.ENGLISH, this);
		myObservers = new ArrayList<VariableObserver>();
		for (ColorObserver colorobs : c){
			myColors.addListener(colorobs);
		}
		
	}
	
	/**
	 * @throws InvalidCommandException 
	 * 
	 */
	public String parseAndExecute(String command) throws InvalidCommandException{
		Command toExec = myParser.getExpressionTreeFromCommand(command);
		toExec.execNonTurtle(myTurtleEnclosure.getActiveTurtle());
		double result = toExec.execute(myTurtleEnclosure.getActiveTurtle());
		
		return Double.toString(result) + "\n";
	}
	
	@Override
	public void changeLanguage(String language) {
		for (Language l : Language.values()) {
			if (l.getLangName().equals(language)) {
				myParser = new MainParser(l, this);
				return;
			}
		}
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

	
	public void addCommandListener(CommandObserver o) {
		myParser.getCommandFactory().addListener(o);
	}
	
	public FileHandler getFileHandler() {
		return myFileHandler;
	}

	@Override
	public void setTurtleImage(String image) {
		myTurtleEnclosure.getActiveTurtle().toTurtleView().setTurtleImage(image);
	}

	@Override
	public double turtleNumber() {
		return myTurtleEnclosure.turtles();
	}

	@Override
	public Pair<Double, TurtleModel> newCompositeTurtleCondition(Command cond) throws InvalidCommandException {
		return myTurtleEnclosure.newCompositeTurtleCondition(cond);
	}

	@Override
	public Pair<Double, TurtleModel> newCompositeTurtle(Command list) throws InvalidCommandException {
		return myTurtleEnclosure.newCompositeTurtle(list);
	}

	@Override
	public void setActiveTurtle(TurtleModel t) {
		myTurtleEnclosure.setActiveTurtle(t);
	}

}
