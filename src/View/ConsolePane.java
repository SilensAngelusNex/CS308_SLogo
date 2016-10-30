package View;


import java.util.ResourceBundle;

import Controller.ModelInViewInterface;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
/**
 * @author Owen Chung
 */
public class ConsolePane extends BorderPane {
	final public static String ENTER_LABEL_KEY = "EnterLabel";
	private static int LineLimit = 11;
	private TextFlow myOutputBox ;
	private TextArea myInputBox;
	private Button myEnterButton;
	private UIFactory myUIFactory;
	private ModelInViewInterface myModelInViewInterface;
	private ListView<String> myCommandHistory;
	
	public ConsolePane(ListView<String> commandHistory, ModelInViewInterface mivinterface, ResourceBundle uilabel){
		myModelInViewInterface = mivinterface;
		myCommandHistory = commandHistory;
		myUIFactory = new UIFactory(uilabel);
		myInputBox = new TextArea();
		createOutputBox();
		myInputBox.setPromptText("Enter your command here");
		myEnterButton = myUIFactory.makeButton(ENTER_LABEL_KEY, event -> parseCommand(myInputBox.getText()));
		HBox inputPane = new HBox(myInputBox, myEnterButton);
		setLeft(inputPane);
		setRight(myOutputBox);
	}

	private void createOutputBox() {
		Text consolelabel = new Text("Console\n");
		consolelabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		consolelabel.setFill(Color.WHITE);
		myOutputBox = new TextFlow(consolelabel);
		myOutputBox.setStyle("-fx-background-color: gray");
		myOutputBox.setPrefSize(400, 200);
	}
	private void parseCommand(String command) {
		try{
			addTexttoInputBox(new Text(myModelInViewInterface.parseAndExecute(command)));
			myCommandHistory.getItems().add(command);
			
		}catch(Exception e){
			myUIFactory.promptAlert("Command Error", e);
		}
	}

	protected TextFlow getPanel() {
		return myOutputBox;
	}
	
	protected void addTexttoInputBox(Text t){
		if (myOutputBox.getChildren().size() == LineLimit){
			myOutputBox.getChildren().remove(1);
		}
		myOutputBox.getChildren().add(t);
	}
	
}
