package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import Model.ColorObserver;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
/**
 * @author Owen Chung
 */
public class HistoryColorPane extends BorderPane implements ColorObserver{
	private Dimension mySize = new Dimension(150, 150);
	private ListView<String> myCommandHistory;
	private TableView<ColorElement> myColorPalette;
	private Map<Color, Integer> myColorMap;
	
	@SuppressWarnings("unchecked")
	protected HistoryColorPane(){
		myColorPalette = new TableView<ColorElement>();
        myColorPalette.setMaxSize(mySize.getWidth(), mySize.getHeight());
        myColorMap = new HashMap<Color, Integer>();
		
        TableColumn<ColorElement, String> colorCol = new TableColumn<ColorElement,String>("Color");
        colorCol.setMaxWidth(75);
        colorCol.setCellValueFactory(new PropertyValueFactory<ColorElement, String>("ColorString"));
		
        TableColumn<ColorElement, Integer> indexCol = new TableColumn<ColorElement, Integer>("Index");
        indexCol.setMaxWidth(75);
        indexCol.setCellValueFactory(new PropertyValueFactory<ColorElement, Integer>("Index"));
        
        myColorPalette.getColumns().addAll(colorCol, indexCol);
		
		
		
		
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(150, 150);
		Label CHLabel = new Label("Command History");
		BorderPane CHpane = new BorderPane();
		CHpane.setTop(CHLabel);
		CHpane.setBottom(myCommandHistory);
		setLeft(CHpane);
		
		BorderPane CPpane = new BorderPane();
		Label CPlabel = new Label("Color Palette");
		CPpane.setTop(CPlabel);
		CPpane.setBottom(myColorPalette);
		setRight(CPpane);
	}
	
	protected ListView<String> getCommandHistory() {
		return myCommandHistory;
	}

	@Override
	public void colorChange(int index, Color newColor) {
		ColorElement newcolor = new ColorElement(newColor, index);
		myColorMap.put(newColor, index);
		myColorPalette.getItems().add(newcolor);
	}

	@Override
	public void backgroundChange(Color newColor) {	
	}
}
