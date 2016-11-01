package View;

import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class SidePane extends BorderPane {
	private ListView<String> myCommandHistory;
	private UserDefinedPane myUserDefinedPane;
	public SidePane(){
		initPane();
	}
	private void initPane() {
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(300, 150);
		myUserDefinedPane = new UserDefinedPane();
		setTop(myCommandHistory);
		setCenter(myUserDefinedPane);
	}
	protected ListView<String> getCommandHistory() {
		return myCommandHistory;
	}
}