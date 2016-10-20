package user_interface;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class SlogoView {
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private ResourceBundle myLanguageResources;
	private Scene myScene;
	
	
    public SlogoView(String language){
    	myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    	BorderPane root = new BorderPane();
    	
    	root.setBottom(makeInputPanel());
    	root.setCenter(makeModelPanel());
    	root.setTop(makeSettingPanel());
    	root.setLeft(makeHTMLManualPanel());
    	root.setRight(makeInputPanel());
    	
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
		// TODO Auto-generated method stub
		return null;
	}

	private Node makeInputPanel() {
		
		return null;
	}

	private TextField makeInputField (int width, EventHandler<ActionEvent> handler) {
        TextField result = new TextField();
        result.setPrefColumnCount(width);
        result.setOnAction(handler);
        return result;
    }
    
	public Scene getScene() {

		return myScene;
	}
}
