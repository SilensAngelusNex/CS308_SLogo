package Controller;

import java.awt.Dimension;

import Model.SlogoModel;
import View.SlogoView;
import View.UserDefinedPane;
//import View.StartScreen;
/**
 * @author Owen Chung
 */
public class SlogoController {
	private SlogoModel myModel;
	private SlogoView myView;
	private UserDefinedPane myUserDefinedPane = new UserDefinedPane();
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	
	public SlogoController(){
		
		myView = new SlogoView("english", myUserDefinedPane);
		myModel = new SlogoModel(myView.getTurtlePane(), myView.getTurtlePane(), DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
		myView.setMakeMultipleWorkspaceInterface(new MultipleWorkspace("english"));
		myModel.addListener(myUserDefinedPane);
		myView.setConsolePane();
		
		/*
		myView = new StartScreen();
		myView.init();
		*/
	}
	
	public SlogoView getView() {
		return myView;
	}

	
	
}
