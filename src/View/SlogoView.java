package View;

import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;

import Model.EnclosureObserver;
import Model.LineModel;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
		root.setTop(makeSettingPanel());
		turtlePane = new Pane();
		turtlePane.setMinWidth(DEFAULT_SIZE.getWidth());
		turtlePane.setMaxWidth(DEFAULT_SIZE.getWidth());
		turtlePane.setMaxHeight(DEFAULT_SIZE.getHeight()/1.5);
		System.out.println(turtlePane.getWidth());
		myModel = new SLOGOModel(null, turtlePane.getWidth(), turtlePane.getHeight());
		root.setLeft(turtlePane);
		turtlePane.setStyle("-fx-background-color: red");
		ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.png")));
		turtle.setFitHeight(50);
		turtle.setFitWidth(50);
		//turtle.setY(DEFAULT_SIZE.getHeight()/1.5);
		//turtlePane.setMinHeight(DEFAULT_SIZE.getHeight());
		//System.out.println(turtlePane.getMinHeight());
		//System.out.println(turtlePane.getMinHeight());
		turtle.relocate(turtlePane.getMaxWidth()/2 - turtle.getFitWidth()/2, turtlePane.getMaxHeight()/2 - turtle.getFitHeight()/2);
		turtlePane.getChildren().add(turtle);
		myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
	}

	private Node makeModelPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private Node makeSettingPanel() {
		HBox functionHBox = new HBox();
		ChoiceBox<String> languageCBox = new ChoiceBox<String>(FXCollections.observableArrayList(
				"Chinese", "English", "French", "German", "Italian", "Portuguese",
				"Russian", "Spanish"));
		ChoiceBox<String> colorCBox = new ChoiceBox<String>(FXCollections.observableArrayList(
				"Black", "Blue", "White"));
		languageCBox.getSelectionModel().select(1);
		languageCBox.setTooltip(new Tooltip("Select the language"));
		languageCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + arg2);
			}

		});
		colorCBox.getSelectionModel().select(2);
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
		ChooseFile fileChooser = new ChooseFile();
		File myImage = fileChooser.chooseFile();
		if (myImage != null){
			myModel.setTurtleImage(myImage.getName());
		}
	}

	private void setBackground() {
		ChooseFile fileChooser = new ChooseFile();
		File myImage = fileChooser.chooseFile();
		if(myImage !=null){
			System.out.println(myImage.getName());
			turtlePane.setStyle("-fx-background-image: url('" + myImage.getName() + "')");
		}

	}

	private Node makeTerminalPanel() {
		BorderPane node = new BorderPane();
		TextArea inputPanel = new TextArea();
		inputPanel.setPromptText("Enter your command here");
		Text text1 = new Text("Console");
		TextFlow console = new TextFlow(text1);
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
		ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(t.getImagePath())));
		turtle.relocate(turtlePane.getMaxWidth() + t.getCurrentLocation().getX(), 
						turtlePane.getMaxHeight() + t.getCurrentLocation().getY());
		turtlePane.getChildren().add(turtle);
	}

	@Override
	public void removeTurtle(TurtleView t) {
		ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(t.getImagePath())));
		turtlePane.getChildren().remove(turtle);
	}

	@Override
	public void moveTurtle(TurtleView t) {	
		
	}

	@Override
	public void addLine(LineModel l) {
		turtlePane.getChildren().add(new Line(turtlePane.getMaxWidth()/2 + l.getStart().getX(), 
											turtlePane.getMaxHeight()/2 - l.getStart().getY(), 
											turtlePane.getMaxWidth() + l.getEnd().getX(), 
											turtlePane.getMaxHeight() - l.getEnd().getY()));

	}

	@Override
	public void removeLine(LineModel l) {	
		turtlePane.getChildren().remove(new Line(l.getStart().getX(), l.getStart().getY(), l.getEnd().getX(), l.getEnd().getY()));
	}
}
