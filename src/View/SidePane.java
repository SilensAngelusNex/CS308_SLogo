package View;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class SidePane extends BorderPane {
	private ListView<String> myCommandHistory;
	private ListView<String> myAvailableVariables;
	private ListView<String> myUserCommands;
	public SidePane(){
		initPane();
	}
	private void initPane() {
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(300, 150);
		setTop(myCommandHistory);
		myAvailableVariables = new ListView<String>();
		myAvailableVariables.setMaxSize(300, 150);
		setCenter(myAvailableVariables);
		myUserCommands = new ListView<String>();
		myUserCommands.setMaxSize(300, 150);
		setBottom(myUserCommands);
	}
	public ListView<String> getCommandHistory() {
		return myCommandHistory;
	}
}
