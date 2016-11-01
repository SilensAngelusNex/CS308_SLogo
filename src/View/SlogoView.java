package View;

import java.awt.Dimension;
import java.io.File;
import java.util.ResourceBundle;

import Controller.ModelInViewInterface;
import Model.SlogoModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import parser.InvalidCommandException;

/**
 * @author Owen Chung, Blake Becerra
 */


public class SlogoView extends BorderPane {
	public static final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	private final String DEFAULT_RESOURCE_PACKAGE = "resources/UIElements";
	private final String LAUGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	private ResourceBundle myLanguageResources;
	private ResourceBundle myUILabel;
	private ModelInViewInterface myModelInViewInterface;
	private UserManualPopup myHelpPage;
	private UIFactory myUIFactory;
	private SidePane mySidePane;
	private TurtlePane myTurtlePane;
	private ConsolePane myConsolePane;
	
	
    public SlogoView(String language){
		myUILabel = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
		myHelpPage = new UserManualPopup();
		myUIFactory = new UIFactory(myUILabel);
		mySidePane = new SidePane();
		myTurtlePane = new TurtlePane();
		setLeft(myTurtlePane);
		setTop(makeToolbar());
		setRight(mySidePane);
	}
    public void setModelInViewInterface(ModelInViewInterface vm){
    	myModelInViewInterface = vm;
    }
    
    public void setConsolePane(){
    	myConsolePane = new ConsolePane(mySidePane.getCommandHistory(), myModelInViewInterface, myUILabel);
		setBottom(myConsolePane);
    }

	private Node makeToolbar() {
		HBox functionHBox = new HBox();
		ChoiceBox<String> languageCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"English", "Chinese", "French", "German", "Italian", "Portuguese",
				"Russian", "Spanish"), "Language");
		
		ChoiceBox<String> colorCBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"Black", "Blue", "White"), "Color");
		ChoiceBox<String> lineColorBox = myUIFactory.makeChoiceBox(FXCollections.observableArrayList(
				"Black",  "Blue", "Red"), "LineColor");
		setLanguageChangeListener(languageCBox);
		setColorChangeListener(colorCBox);
		setLineColorChangeListener(lineColorBox);
		Button BackgroundButton = myUIFactory.makeButton("BackgroundLabel", event -> setBackground());
		Button TurtleDisplyButton = myUIFactory.makeButton("TurtleLabel", event -> displayTurtle());
		Button HelpButton = myUIFactory.makeButton("HelpLabel", event -> promptHelpPage());
		Button workspaceButton = myUIFactory.makeButton("NewWorkspaceLabel", e -> makeNewWorkspace());
		functionHBox.getChildren().addAll(languageCBox, colorCBox, lineColorBox, BackgroundButton, 
				TurtleDisplyButton, HelpButton, workspaceButton);
		return functionHBox;
	}
	
	private void setLineColorChangeListener(ChoiceBox<String> lineColorBox){
		lineColorBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				try {
					myModelInViewInterface.parseAndExecute(myLanguageResources.getString("SetPenColor") 
											+ " " + lineColorBox.getSelectionModel().getSelectedIndex());
				} catch (InvalidCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void setColorChangeListener(ChoiceBox<String> colorCBox) {
		colorCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				myTurtlePane.setStyle("-fx-background-color: " + arg2);
			}

		});
	}

	private void setLanguageChangeListener(ChoiceBox<String> languageCBox) {
		languageCBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + arg2);
			}

		});
	}

	private void promptHelpPage() {
		Stage newstage = new Stage();
		myHelpPage.start(newstage);
	}

	private void displayTurtle() {
		ChooseFile fileChooser = new ChooseFile();
		File myImage = fileChooser.chooseFile();
		if (myImage != null){
			myTurtlePane.changeTurtleImage(myImage.getName());;
			myModelInViewInterface.setTurtleImage(myImage.getName());
		}
	}
	
	private void makeNewWorkspace(){
		SlogoView workspaceView = new SlogoView("english");
		SlogoModel workspaceModel = new SlogoModel(workspaceView.getTurtlePane(), DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		workspaceView.setModelInViewInterface((ModelInViewInterface) workspaceModel);
		workspaceView.setConsolePane();
	//	Scene newScene = new Scene(workspaceView, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		Stage newWorkspace = new Stage();
		newWorkspace.setScene(new Scene(workspaceView, DEFAULT_SIZE.width, DEFAULT_SIZE.height));
		newWorkspace.show();
	}

	private void setBackground() {
		ChooseFile fileChooser = new ChooseFile();
		File myImage = fileChooser.chooseFile();
		if(myImage !=null){
			myTurtlePane.setStyle("-fx-background-image: url('" + myImage.getName() + "')");
		}

	}
	public TurtlePane getTurtlePane() {
		return myTurtlePane;
	}

}
