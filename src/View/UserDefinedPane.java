package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import Model.VariableObserver;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class UserDefinedPane extends BorderPane implements VariableObserver{
	private Dimension mySize = new Dimension(150, 150);
	private TableView<Variable> myAvailableVariables;
	private ListView<String> myUserCommands;
	private Map<String, Double> myVariableMap;
	
	public UserDefinedPane(){
		initPanes();
		
	}

	@SuppressWarnings("unchecked")
	private void initPanes() {
		myAvailableVariables = new TableView<Variable>();
		myUserCommands = new ListView<String>();
		myVariableMap = new HashMap<String, Double>();
		myAvailableVariables.setMaxSize(mySize.getWidth(), mySize.getHeight());
		TableColumn<Variable, String> variableCol = new TableColumn<Variable,String>("Variable");
		variableCol.setMaxWidth(75);
		variableCol.setCellValueFactory(new PropertyValueFactory<Variable, String>("VariableString"));
		
        TableColumn<Variable, Double> valueCol = new TableColumn<Variable, Double>("Value");
        valueCol.setMaxWidth(75);
        valueCol.setCellValueFactory(new PropertyValueFactory<Variable, Double>("Value"));
       
        myAvailableVariables.getColumns().addAll(variableCol, valueCol);
        
		
        myUserCommands.setMaxSize(mySize.getWidth(), mySize.getHeight());
		
//		myAvailableVariables.setItems(FXCollections.observableArrayList());
		BorderPane rightPane = new BorderPane();
		rightPane.setTop(new Label("Available Variables"));
		rightPane.setBottom(myAvailableVariables);
		setRight(rightPane);
		
		BorderPane leftPane = new BorderPane();
		leftPane.setTop(new Label("Available Commands"));
		leftPane.setBottom(myUserCommands);
		setLeft(leftPane);
		
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
		Variable newvar = new Variable(varVame, newValue);
		myAvailableVariables.getItems().add(newvar);
		
	}

	@Override
	public void deleteVariable(String varVame) {
		double oldValue = myVariableMap.get(varVame);
		Variable oldvar = new Variable(varVame, oldValue);
		
		myAvailableVariables.getItems().remove(oldvar);
	}
	
}
