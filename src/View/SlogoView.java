package View;
import java.awt.Dimension;
import java.util.ResourceBundle;

import Controller.MakeMultipleWorkspaceInterface;
import Controller.ModelInViewInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
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
	private MakeMultipleWorkspaceInterface myMultipleWorkspaceInterface;
	private UserManualPopup myHelpPage;
	private UIFactory myUIFactory;
	private SidePane mySidePane;



	private TurtlePane myTurtlePane;
	private ConsolePane myConsolePane;
	private ToolBar myToolBar;
	
	
    public SlogoView(String language, UserDefinedPane udp ){
		myUILabel = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		myLanguageResources = ResourceBundle.getBundle(LAUGUAGE_RESOURCE_PACKAGE + language);
		myHelpPage = new UserManualPopup();
		myUIFactory = new UIFactory(myUILabel);
		mySidePane = new SidePane(udp);
		myTurtlePane = new TurtlePane();
		setLeft(myTurtlePane);
		initToolbar();
		setTop(myToolBar);
		setRight(mySidePane);
	}
    
    /**
     * Purpose: Set the ModelInViewInterace 
     * @param vm
     */
    public void setModelInViewInterface(ModelInViewInterface vm){
    	myModelInViewInterface = vm;
    }
    
    /**
     * Purpose: Set the multiple workspace interface
     * @param mw
     */
    public void setMakeMultipleWorkspaceInterface(MakeMultipleWorkspaceInterface mw){
    	myMultipleWorkspaceInterface = mw;
    }
    
    /**
     * Makes the console pane to be displayed
     */
    public void setConsolePane(){
    	myConsolePane = new ConsolePane(mySidePane.getCommandHistory(), myModelInViewInterface, myUILabel);
		setBottom(myConsolePane);
    }
    
	private void initToolbar() {
		myToolBar = new ToolBar(myUILabel, myHelpPage, myModelInViewInterface, myTurtlePane);
		setLanguageChangeListener(myToolBar.getLanguageCBox());
		setColorChangeListener(myToolBar.getColorCBox());
		setLineColorChangeListener(myToolBar.getLineColorCBox());
		Button workspaceButton = myUIFactory.makeButton("NewWorkspaceLabel", e -> makeNewWorkspace());
		myToolBar.getChildren().add(workspaceButton);
	}
	
	private void setLineColorChangeListener(ChoiceBox<String> lineColorBox){
		lineColorBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				try {
					myModelInViewInterface.parseAndExecute(myLanguageResources.getString("SetPenColor").split("\\|")[0] 
											+ " " + lineColorBox.getSelectionModel().getSelectedIndex());
				} catch (InvalidCommandException e) {
					myUIFactory.promptAlert("Command Error", e);
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
				myModelInViewInterface.changeLanguage(arg2);
			}

		});
	}
	
	private void makeNewWorkspace(){
		myMultipleWorkspaceInterface.makeWorkspace();
	}

	/**
	 * Purpose: Get the TurtlePane for the display
	 * @return
	 */
	public TurtlePane getTurtlePane() {
		return myTurtlePane;
	}
	
	/**
	 * Purpose : Gets History color pane for display
	 * @return
	 */
	public HistoryColorPane getHistoryColorPane() {
		return mySidePane.getHistoryColorPane();
	}

}
