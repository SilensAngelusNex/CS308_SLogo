package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.CommandObserver;
import Model.VariableObserver;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class UserDefinedPane extends BorderPane implements VariableObserver, CommandObserver{
	private Dimension mySize = new Dimension(150, 150);
	private TableView<Variable> myAvailableVariables;
	private TableView<Command> myUserCommands;
	private Map<String, Double> myVariableMap;
	private Map<String, Integer> myCommandMap;

	
	public UserDefinedPane(){
		initPanes();
		
	}

	@SuppressWarnings("unchecked")
	private void initPanes() {
		makeAvailabeleVariables();
        myUserCommands = new TableView<Command>();
        myUserCommands.setMaxSize(mySize.getWidth(), mySize.getHeight());
        myCommandMap = new HashMap<String, Integer>();
        
        TableColumn<Command, String> commandCol = new TableColumn<Command,String>("Command");
        commandCol.setMaxWidth(75);
        commandCol.setCellValueFactory(new PropertyValueFactory<Command, String>("CommandString"));
		
        TableColumn<Command, Integer> numargsCol = new TableColumn<Command, Integer>("# args");
        numargsCol.setMaxWidth(75);
        numargsCol.setCellValueFactory(new PropertyValueFactory<Command, Integer>("NumofArguments"));
        
        myUserCommands.getColumns().addAll(commandCol, numargsCol);
        
		BorderPane rightPane = new BorderPane();
		rightPane.setTop(new Label("Available Variables"));
		rightPane.setBottom(myAvailableVariables);
		setRight(rightPane);
		
		BorderPane leftPane = new BorderPane();
		leftPane.setTop(new Label("Available Commands"));
		leftPane.setBottom(myUserCommands);
		setLeft(leftPane);
		
	}

	@SuppressWarnings("unchecked")
	private void makeAvailabeleVariables() {
		myAvailableVariables = new TableView<Variable>();
		myVariableMap = new HashMap<String, Double>();
		myAvailableVariables.setMaxSize(mySize.getWidth(), mySize.getHeight());
		TableColumn<Variable, String> variableCol = new TableColumn<Variable,String>("Variable");
		variableCol.setMaxWidth(75);
		variableCol.setCellValueFactory(new PropertyValueFactory<Variable, String>("VariableString"));
		
        TableColumn<Variable, Double> valueCol = new TableColumn<Variable, Double>("Value");
        valueCol.setMaxWidth(75);
        valueCol.setCellValueFactory(new PropertyValueFactory<Variable, Double>("Value"));
       
        myAvailableVariables.getColumns().addAll(variableCol, valueCol);
	}

	@Override
	public void addVariable(String varVame, double value) {
		Variable tmpvar = new Variable(varVame, value);
		myVariableMap.put(varVame, value);
		myAvailableVariables.getItems().add(tmpvar);
	
	}

	@Override
	public void changeVariable(String varVame, double newValue) {
		System.out.println("change variable");
		double oldValue = myVariableMap.get(varVame);
		Variable oldvar = new Variable(varVame, oldValue);
		myAvailableVariables.getItems().remove(oldvar);
		myVariableMap.put(varVame, newValue);
		Variable newvar = new Variable(varVame, newValue);
		myAvailableVariables.getItems().add(newvar);
		
	}

	@Override
	public void deleteVariable(String varVame) {
		double oldValue = myVariableMap.get(varVame);
		Variable oldvar = new Variable(varVame, oldValue);
		myVariableMap.remove(oldvar);
		myAvailableVariables.getItems().remove(oldvar);
	}

	@Override
	public void addCommand(String commandName, List<String> args, int numArgs) {
		System.out.println("adding a command");
		Command newcommand = new Command(commandName, numArgs);
		if (myCommandMap.containsKey(commandName)){
			Command oldcommand = new Command(commandName, myCommandMap.get(commandName));
			myUserCommands.getItems().remove(oldcommand);
		}
		myCommandMap.put(commandName, numArgs);
		myUserCommands.getItems().add(newcommand);

	}
	
}
