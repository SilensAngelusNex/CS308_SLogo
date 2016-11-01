package View;

import java.io.File;
import java.util.ResourceBundle;

import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ToolBar extends HBox{
	private UIFactory myUIFactory;
	private ChoiceBox<String> myLanguageCBox;
	private ChoiceBox<String> myColorCBox;
	private UserManualPopup myHelpPage;
	public ToolBar(ResourceBundle uilabel, UserManualPopup helppage){
		myUIFactory = new UIFactory(uilabel);
		myHelpPage = helppage;
		myLanguageCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"English", "Chinese", "French", "German", "Italian", "Portuguese",
				"Russian", "Spanish"), "Language");
		
		myColorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"Black", "Blue", "White"), "Color");
		Button BackgroundButton = myUIFactory.makeButton("BackgroundLabel", event -> setBackground());
		Button TurtleDisplyButton = myUIFactory.makeButton("TurtleLabel", event -> displayTurtle());
		Button HelpButton = myUIFactory.makeButton("HelpLabel", event -> promptHelpPage());
		getChildren().addAll(myLanguageCBox, myColorCBox, BackgroundButton, TurtleDisplyButton, HelpButton);
	}

	private File chooseFiletoRead() {
		ChooseFile fileChooser = new ChooseFile();
		File ret = fileChooser.chooseFile();
		return ret;
	}
	private void setBackground() {
		if(chooseFiletoRead() != null){
//		myTurtlePane.setStyle("-fx-background-image: url('" + myImage.getName() + "')");
		}

	}
	private void displayTurtle() {
		if (chooseFiletoRead() != null){
//			myModelInViewInterface.setTurtleImage(myImage.getName());
		}
	}
	
	private void promptHelpPage() {
		Stage newstage = new Stage();
		myHelpPage.start(newstage);
	}
}
