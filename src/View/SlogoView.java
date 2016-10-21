package View;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;


public class SlogoView {
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/UILabels";
	private final String LAUGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myLanguageResources;
	private ResourceBundle myResources;
	private Scene myScene;
	 private WebView myHelpPage;
	
    public SlogoView(String language){
    	myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
    	myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
    	BorderPane root = new BorderPane();
    	
    	root.setBottom(makeTerminalPanel());
    	//root.setCenter(makeModelPanel());
    	//root.setTop(makeSettingPanel());
    	root.setLeft(makeHTMLManualPanel());
    	//root.setRight(makeInputPanel());
    	
    	myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }
    
    private Node makeModelPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node makeSettingPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node makeHTMLManualPanel() {
		myHelpPage = new WebView();
		myHelpPage.getEngine().load("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php");
		return myHelpPage;
	}

	private Node makeTerminalPanel() {
		BorderPane node = new BorderPane();
		TextArea inputPanel = new TextArea();
		inputPanel.setPromptText("Enter your command here");
		Text text1 = new Text("Console");
		TextFlow console = new TextFlow(text1);
		Button enterbutton = makeButton("EnterLabel", event -> parseCommand());
		node.setLeft(inputPanel);
		node.setCenter(enterbutton);
		node.setRight(console);
		
		
		return node;
	}
	private void parseCommand() {
	}

	private Button makeButton (String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = myResources.getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }
	
	

    
	public Scene getScene() {

		return myScene;
	}
}
