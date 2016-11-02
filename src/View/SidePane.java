package View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class SidePane extends BorderPane {
	private UserDefinedPane myUserDefinedPane;
	private HistoryColorPane myHistoryColorPane;

	private BorderPane myStatePane = new BorderPane();
	public SidePane(UserDefinedPane udp){
		myUserDefinedPane = udp;
		myHistoryColorPane = new HistoryColorPane();
		myStatePane.setTop(new Label("States of Turtle"));
		ListView<String> stateView = new ListView<String>();
		stateView.setMaxSize(300, 150);
		myStatePane.setBottom(stateView);
		setTop(myHistoryColorPane);
		setCenter(myUserDefinedPane);
		setBottom(myStatePane);
	}
	protected ListView<String> getCommandHistory() {
		return myHistoryColorPane.getCommandHistory();
	}
	protected UserDefinedPane getUserDefinePane(){
		return myUserDefinedPane;

	}
	
	public HistoryColorPane getHistoryColorPane() {
		return myHistoryColorPane;
	}
}