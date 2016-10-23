package View;

import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Model.EnclosureObserver;
import Model.Line;
import Model.Observable;
import Model.SLOGOModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class SlogoView implements EnclosureObserver{
	private final Dimension DEFAULT_SIZE = new Dimension(800, 600);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/UILabels";
	private final String LAUGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myLanguageResources;
	private ResourceBundle myUILabel;
	private Scene myScene;
	private UserManualPopup myHelpPage;
	private Pane turtlePane;
	private SLOGOModel myModel;
	
    public SlogoView(String language){
    	myUILabel = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
    	myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
    
    	BorderPane root = new BorderPane();
    	myHelpPage = new UserManualPopup();
    	root.setBottom(makeTerminalPanel());
    	//root.setCenter(makeModelPanel());
    	root.setTop(makeSettingPanel());
    	turtlePane = new Pane();
    	turtlePane.setMinWidth(DEFAULT_SIZE.getWidth());
    	Rectangle r = new Rectangle(100, 100, Color.BLACK);
    	r.relocate(50, 50);
    	turtlePane.getChildren().add(r);
    	myModel = new SLOGOModel(null, turtlePane.getWidth(), turtlePane.getHeight());
    	root.setLeft(turtlePane);
    	turtlePane.setStyle("-fx-background-color: red");
    	//root.setLeft(makeHTMLManualPanel());
    	//root.setRight(makeInputPanel());
    	
    	myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }
    
    private Node makeModelPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private Node makeSettingPanel() {
		HBox functionHBox = new HBox();
		ChoiceBox languageCBox = new ChoiceBox(FXCollections.observableArrayList(
				"Chinese", "English", "French", "German", "Italian", "Portuguese",
				"Russian", "Spanish"));
		ChoiceBox colorCBox = new ChoiceBox(FXCollections.observableArrayList(
				"Black", "Blue", "White"));
		languageCBox.getSelectionModel().select(1);
		colorCBox.getSelectionModel().select(0);
		languageCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + arg2);
			}
			
		});
		colorCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				turtlePane.setStyle("-fx-background-color: " + arg2);
			}
		
		});
		Button BackgroundButton = makeButton("BackgroundLabel", event -> setBackground());
		Button TurtleDisplyButton = makeButton("TurtleLabel", event -> displayTurtle());
		Button HelpButton = makeButton("HelpLabel", event -> promptHelpPage());
		functionHBox.getChildren().addAll(languageCBox, colorCBox, BackgroundButton, TurtleDisplyButton, HelpButton);
		return functionHBox;
	}

	private void promptHelpPage() {
		Stage newstage = new Stage();
		myHelpPage.start(newstage);
	}

	private void displayTurtle() {
		Rectangle r = new Rectangle();
		r.setFill(Color.BLACK);
		turtlePane.getChildren().add(r);
		
	}

	private void setBackground() {
		ChooseFile fileChooser = new ChooseFile();
		File myImage = fileChooser.chooseFile();
		System.out.println(myImage.getName());
		turtlePane.setStyle("-fx-background-image: url('" + myImage.getName() + "')");
	}

	private Node makeTerminalPanel() {
		BorderPane node = new BorderPane();
		TextArea inputPanel = new TextArea();
		inputPanel.setPromptText("Enter your command here");
		Text text1 = new Text("Console");
		TextFlow console = new TextFlow(text1);
		//System.out.println(console.getChildren().toString());
		Button enterbutton = makeButton("EnterLabel", event -> parseCommand(inputPanel.getText()));
		node.setLeft(inputPanel);
		node.setCenter(enterbutton);
		node.setRight(console);
		return node;
	}
	private void parseCommand(String command) {
		
		
	}

	private Button makeButton (String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        String label = myUILabel.getString(property);
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }
    
	public Scene getScene() {
		return myScene;
	}

	@Override
	public void addTurtle(TurtleView t) {
		
		Rectangle r = new Rectangle(100, 100, Color.BLACK);
    	turtlePane.getChildren().add(r);
//		ImageView turtle = new ImageView(t.getImagePath());
//		turtle.relocate(t.getCurrentLocation().getX(), t.getCurrentLocation().getY());
//		turtlePane.getChildren().add(turtle);
	}

	@Override
	public void removeTurtle(TurtleView t) {
		// TODO Auto-generated method stub
		ImageView turtle = new ImageView(t.getImagePath());
		turtlePane.getChildren().remove(turtle);
	}

	@Override
	public void moveTurtle(TurtleView t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLine(Line l) {
		//turtlePane.getChildren().add(l);
	}

	@Override
	public void removeLine(Line l) {
		turtlePane.getChildren().remove(l);
		
	}
}
