package View;

import java.io.File;
import java.util.ResourceBundle;

import Controller.ModelInViewInterface;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ToolBar extends HBox{
	private UIFactory myUIFactory;
	private TurtlePane myTurtlePane;
	private ModelInViewInterface myModelInViewInterface;
	private ChoiceBox<String> myLanguageCBox;
	
	private ChoiceBox<String> myColorCBox;


	private ChoiceBox<String> myLineColorCBox;

	private UserManualPopup myHelpPage;
	
	public ToolBar(ResourceBundle uilabel, UserManualPopup helppage, ModelInViewInterface MVI, 	TurtlePane turtlepane){
		myUIFactory = new UIFactory(uilabel);
		myHelpPage = helppage;
		myTurtlePane = turtlepane;
		myModelInViewInterface = MVI;
		myLanguageCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"English", "Chinese", "French", "German", "Italian", "Portuguese",
				"Russian", "Spanish"), "Language");
		
		myColorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"Black", "Blue", "White"), "Color");
		
		myLineColorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"Black",  "Blue", "Red"), "LineColor");
		
		Button BackgroundButton = myUIFactory.makeButton("BackgroundLabel", event -> setBackground());
		Button TurtleDisplyButton = myUIFactory.makeButton("TurtleLabel", event -> displayTurtle());
		Button HelpButton = myUIFactory.makeButton("HelpLabel", event -> promptHelpPage());
		getChildren().addAll(myLanguageCBox, myColorCBox, myLineColorCBox, BackgroundButton, TurtleDisplyButton, HelpButton);
	}

	private File chooseFiletoRead() {
		ChooseFile fileChooser = new ChooseFile();
		File ret = fileChooser.chooseFile();
		return ret;
	}
	private void setBackground() {
		File file = chooseFiletoRead();
		if(file != null){
			//TODO : call backend instead
		myTurtlePane.setStyle("-fx-background-image: url('" + file.getName() + "')");
		}

	}
	private void displayTurtle() {
		File file = chooseFiletoRead();
		if (file != null){
			myModelInViewInterface.setTurtleImage(file.getName());
		}
	}
	
	private void promptHelpPage() {
		Stage newstage = new Stage();
		myHelpPage.start(newstage);
	}
	
	public ChoiceBox<String> getLanguageCBox() {
		return myLanguageCBox;
	}
	
	public ChoiceBox<String> getColorCBox() {
		return myColorCBox;
	}
	
	public ChoiceBox<String> getLineColorCBox() {
		return myColorCBox;
	}
	
}
