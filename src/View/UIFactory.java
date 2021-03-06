package View;

import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Owen Chung and Blake Becerra
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
			retBox.getSelectionModel().selectFirst();
			retBox.setTooltip(new Tooltip("Select the color of the background"));
		}
		else if(Type.equals("LineColor")){
			retBox.getSelectionModel().select(1);
			retBox.setTooltip(new Tooltip("Select the color of the pen"));
		}
		return retBox;
	}

	protected Label makeLabel(String labeltext){
		return new Label();
	}

	protected void promptAlert(String s, Exception e){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(s);
		alert.setHeaderText(s);
		alert.setContentText(e.toString());
		alert.show();
	}
}
