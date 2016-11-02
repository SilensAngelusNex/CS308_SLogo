package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import Model.VariableObserver;
//import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class UserDefinedPane extends BorderPane implements VariableObserver{
	private Dimension mySize = new Dimension(150, 150);
	private ListView<String> myAvailableVariables;
	private ListView<String> myUserCommands;
	private Map<String, Double> myValueMap;
	
	public UserDefinedPane(){
		initPanes();
		
	}

	private void initPanes() {
		myAvailableVariables = new ListView<String>();
		myUserCommands = new ListView<String>();
		myValueMap = new HashMap<String, Double>();		
		myAvailableVariables.setMaxSize(mySize.getWidth(), mySize.getHeight());
		myAvailableVariables.getItems().add("variable" + "        " + "value");
		myUserCommands.setMaxSize(mySize.getWidth(), mySize.getHeight());
		
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
		myValueMap.put(varVame, value);
		System.out.println("adding a variable");
		myAvailableVariables.getItems().add(varVame + "        " + value);
	
	}

	@Override
	public void changeVariable(String varVame, double newValue) {
		int index = myAvailableVariables.getItems().indexOf(varVame+ "        " + myValueMap.get(varVame));
		String newstring = varVame+ "        " + newValue;
		myAvailableVariables.getItems().set(index, newstring);
		myValueMap.put(varVame, newValue);
	}

	@Override
	public void deleteVariable(String varVame) {
		myValueMap.remove(varVame+ "        " + myValueMap.get(varVame));
		myAvailableVariables.getItems().remove(varVame);
	}
}
