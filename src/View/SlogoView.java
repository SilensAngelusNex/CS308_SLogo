package View;

import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import Controller.ModelInViewInterface;
import Model.EnclosureObserver;
import Model.LineModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Owen Chung, Blake Becerra
 */


public class SlogoView extends BorderPane implements EnclosureObserver{
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/UIElements";
	private final String LAUGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myLanguageResources;
	private ResourceBundle myUILabel;
	private ModelInViewInterface myModelInViewInterface;
	private UserManualPopup myHelpPage;
	private UIFactory myUIFactory;
	private Pane turtlePane;
	private ConsolePane myConsolePane;
	private ListView<String> myCommandHistory;
	private ListView<String> myAvailableVariables;
	private ListView<String> myUserCommands;
	private Map<TurtleView, ImageView> myTurtleImages;
	private Map<LineModel, Line> myLines;
	
	
    public SlogoView(String language){
		myUILabel = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
		myHelpPage = new UserManualPopup();
		myUIFactory = new UIFactory(myUILabel);
		myTurtleImages = new HashMap<TurtleView, ImageView>();
		myLines = new HashMap<LineModel, Line>();
		turtlePane = new Pane();
		turtlePane.setMinWidth(DEFAULT_SIZE.getWidth() * 0.7);
		turtlePane.setMaxWidth(DEFAULT_SIZE.getWidth() * 0.7);
		turtlePane.setMinHeight(DEFAULT_SIZE.getHeight() / 1.5);
		turtlePane.setMaxHeight(DEFAULT_SIZE.getHeight()/1.5);
		turtlePane.setStyle("-fx-background-color: white");
		setTop(makeSettingPane());
		setRight(makeHistoryPane());
		setLeft(turtlePane);
		
	}
    
    public void setModelInViewInterface(ModelInViewInterface vm){
    	myModelInViewInterface = vm;
    }
    
    public void setConsolePane(){
    	myConsolePane = new ConsolePane(myCommandHistory, myModelInViewInterface, myUILabel);
		setBottom(myConsolePane);
    }
    

	private Node makeHistoryPane() {
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


	private Node makeSettingPane() {
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
		Button BackgroundButton = myUIFactory.makeButton("BackgroundLabel", event -> setBackground());
		Button TurtleDisplyButton = myUIFactory.makeButton("TurtleLabel", event -> displayTurtle());
		Button HelpButton = myUIFactory.makeButton("HelpLabel", event -> promptHelpPage());
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
			myModelInViewInterface.setTurtleImage(myImage.getName());
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
		Line lineToAdd = new Line(turtlePane.getMaxWidth() / 2 + l.getStart().getX(), 
				turtlePane.getMaxHeight() / 2 - l.getStart().getY(), 
				turtlePane.getMaxWidth() / 2 + l.getEnd().getX(), 
				turtlePane.getMaxHeight() /2 - l.getEnd().getY());
		myLines.put(l, lineToAdd);
		turtlePane.getChildren().add(lineToAdd);
	}

	@Override
	public void removeLine(LineModel l) {	
		turtlePane.getChildren().remove(myLines.get(l));
	}
}
