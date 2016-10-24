package View;

import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SlogoView implements EnclosureObserver{
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/UILabels";
	private final String LAUGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myLanguageResources;
	private ResourceBundle myUILabel;
	private Scene myScene;
	private UserManualPopup myHelpPage;
	private Pane turtlePane;
	private SLOGOModel myModel;
	private Console myConsole;
	private ListView<String> myCommandHistory;
	private ListView<String> myAvailableVariables;
	private ListView<String> myUserCommands;
	private Map<TurtleView, ImageView> myTurtleImages;
	
	
    public SlogoView(String language){
		myUILabel = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
		BorderPane root = new BorderPane();
		myHelpPage = new UserManualPopup();
		myConsole = new Console();
		myTurtleImages = new HashMap<TurtleView, ImageView>();
		root.setBottom(makeTerminalPanel());
		root.setTop(makeSettingPanel());
		root.setRight(makeHistoryPanel());
		turtlePane = new Pane();
		turtlePane.setMinWidth(DEFAULT_SIZE.getWidth() * 0.7);
		turtlePane.setMaxWidth(DEFAULT_SIZE.getWidth() * 0.7);
		turtlePane.setMinHeight(DEFAULT_SIZE.getHeight() / 1.5);
		turtlePane.setMaxHeight(DEFAULT_SIZE.getHeight()/1.5);
		myModel = new SLOGOModel(this, turtlePane.getMaxWidth() / 2, turtlePane.getMaxHeight() / 2);
		root.setLeft(turtlePane);
		turtlePane.setStyle("-fx-background-color: white");
		myScene = new Scene(root, DEFAULT_SIZE.width, DEFAULT_SIZE.height);

	}


	private Node makeHistoryPanel() {
		BorderPane infopane = new BorderPane();
		myCommandHistory = new ListView<String>();
		myCommandHistory.setMaxSize(300, 150);
		myAvailableVariables = new ListView<String>();
		myAvailableVariables.setMaxSize(300, 150);
		myUserCommands = new ListView<String>();
		myUserCommands.setMaxSize(300, 150);
		infopane.setTop(myCommandHistory);
		infopane.setCenter(myAvailableVariables);
		infopane.setBottom(myUserCommands);
		return infopane;
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
		TextArea inputTexts = new TextArea();
		inputTexts.setPromptText("Enter your command here");
		Button enterbutton = makeButton("EnterLabel", event -> parseCommand(inputTexts.getText()));
		HBox inputPanel = new HBox(inputTexts, enterbutton);
		node.setLeft(inputPanel);
		node.setCenter(enterbutton);
		node.setRight(myConsole.getPanel());
		return node;
	}
	private void parseCommand(String command) {
		try{
			String result = myModel.parseAndExecute(command);
			myConsole.getPanel().getChildren().add(new Text(result));
			myCommandHistory.getItems().add(command);
			
		}catch(Exception e){
			promptAlert("Command Error", e);
		}
	}

	private void promptAlert(String s, Exception e){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(s);
		alert.setHeaderText(s);
		alert.setContentText(e.toString());
		alert.show();
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
		myTurtleImages.put(t, turtle);
		turtle.setRotate(t.getCurrentHeading());
		turtle.relocate(turtlePane.getMaxWidth() / 2 - turtle.getBoundsInLocal().getWidth() / 2 + t.getCurrentLocation().getX(), 
						turtlePane.getMaxHeight() /2  - turtle.getBoundsInLocal().getHeight() / 2 - t.getCurrentLocation().getY());
		turtlePane.getChildren().add(turtle);
	}

	@Override
	public void removeTurtle(TurtleView t) {
		ImageView turtle = myTurtleImages.get(t);
		turtlePane.getChildren().remove(turtle);
	}

	@Override
	public void moveTurtle(TurtleView t) {	
		ImageView turtle = myTurtleImages.get(t);
		turtle.setRotate(t.getCurrentHeading());
		turtle.relocate(turtlePane.getMaxWidth() / 2 - turtle.getBoundsInLocal().getWidth() / 2 + t.getCurrentLocation().getX(), 
				turtlePane.getMaxHeight() /2  - turtle.getBoundsInLocal().getHeight() / 2 - t.getCurrentLocation().getY());
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
