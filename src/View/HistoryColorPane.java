package View;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
/**
 * @author Owen Chung
 */
public class HistoryColorPane extends BorderPane{
	private ListView<String> myCommandHistory;
	private ListView<String> myColorPalette;
	
	
	protected HistoryColorPane(){
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(150, 150);
		Label CHLabel = new Label("Command History");
		BorderPane CHpane = new BorderPane();
		CHpane.setTop(CHLabel);
		CHpane.setBottom(myCommandHistory);
		setLeft(CHpane);
		
		myColorPalette = new ListView<String>();
		myColorPalette.setMaxSize(150, 150);
		BorderPane CPpane = new BorderPane();
		Label CPlabel = new Label("Color Palette");
		CPpane.setTop(CPlabel);
		CPpane.setBottom(myColorPalette);
		setRight(CPpane);
	}
	
	protected ListView<String> getCommandHistory() {
		return myCommandHistory;
	}
}
