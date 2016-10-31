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
	private BorderPane myUserDefinedPane;
	public SidePane(){
		initPane();
	}
	private void initPane() {
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(300, 150);
		initUserDefinedPane();
		setTop(myCommandHistory);

	}
	protected ListView<String> getCommandHistory() {
		return myCommandHistory;
	}
}