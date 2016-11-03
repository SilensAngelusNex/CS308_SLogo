package View;

import java.io.File;
import java.util.ResourceBundle;

import Controller.ModelInViewInterface;
import javafx.scene.control.ChoiceBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 * @author Owen Chung
 */

import parser.Language;

public class ToolBar extends HBox{
	private UIFactory myUIFactory;
	private TurtlePane myTurtlePane;
	//private ModelInViewInterface myModelInViewInterface;
	private ChoiceBox<String> myLanguageCBox;
	
	private ChoiceBox<String> myColorCBox;


	private ChoiceBox<String> myLineColorCBox;

	private UserManualPopup myHelpPage;
	
	public ToolBar(ResourceBundle uilabel, UserManualPopup helppage, ModelInViewInterface MVI, 	TurtlePane turtlepane){
		myUIFactory = new UIFactory(uilabel);
		myHelpPage = helppage;
		myTurtlePane = turtlepane;
		//myModelInViewInterface = MVI;
		
		ObservableList<String> languages = FXCollections.observableArrayList();
		for (Language lang : Language.values()) {
			languages.add(lang.getLangName());
		}
		
		myLanguageCBox = myUIFactory.makeChoiceBox(languages, "Language");
		
		myColorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"White",  "Black", "Red", "Blue", "Green", "Aqua", 	"Fuchsia", "Yellow"), "Color");
		
		myLineColorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"White",  "Black", "Red", "Green", "Blue", "Aqua", 	"Fuchsia", "Yellow"), "LineColor");
		
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
			myTurtlePane.changeTurtleImage(file.getName());
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
		return myLineColorCBox;
	}
	
}
