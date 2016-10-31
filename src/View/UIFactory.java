package View;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Owen Chung
 */

public class UIFactory {
	private ResourceBundle myUILabel;
	
	protected UIFactory(ResourceBundle uilabel){
		myUILabel = uilabel;
	}
	
	protected Button makeButton (String property, EventHandler<ActionEvent> handler) {
		Button result = new Button();
		String label = myUILabel.getString(property);
		result.setText(label);
		result.setOnAction(handler);
		return result;
	}
	
	protected ChoiceBox<String> makeChoiceBox(ObservableList<String> choices, String Type){
		ChoiceBox<String> retBox = new ChoiceBox<String>(choices);
		if(Type.equals("Language")){
			retBox.getSelectionModel().selectFirst();
			retBox.setTooltip(new Tooltip("Select the language"));
			
		}
		else if(Type.equals("Color")){
			retBox.getSelectionModel().selectLast();
		}
		return retBox;
	}
	
	

}
