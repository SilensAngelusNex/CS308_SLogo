package View;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;

public class ToolBar extends HBox{
	private UIFactory myUIFactory;
	private ChoiceBox<String> myLanguageCBox;
	private ChoiceBox<String> myColorCBox;
	private UserManualPopup myHelpPage;
	public ToolBar(ResourceBundle uilabel){
		myUIFactory = new UIFactory(uilabel);
		
//		Button BackgroundButton = myUIFactory.makeButton("BackgroundLabel", event -> setBackground());
//		Button TurtleDisplyButton = myUIFactory.makeButton("TurtleLabel", event -> displayTurtle());
//		Button HelpButton = myUIFactory.makeButton("HelpLabel", event -> promptHelpPage());
//		getChildren().addAll(myLanguageCBox, myColorCBox, BackgroundButton, TurtleDisplyButton, HelpButton);
	}
}
