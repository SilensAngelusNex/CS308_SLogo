package Controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import Model.ColorObserver;
import Model.CommandObserver;
import Model.SlogoModel;
import Model.VariableObserver;
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
		List<ColorObserver> colorobs = new ArrayList<ColorObserver>();
		colorobs.add((ColorObserver)myView.getTurtlePane());
		colorobs.add((ColorObserver) myView.getHistoryColorPane());
		myModel = new SlogoModel(myView.getTurtlePane(), colorobs, DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
		myView.setMakeMultipleWorkspaceInterface(new MultipleWorkspace("english"));
		myModel.addListener((VariableObserver)myUserDefinedPane);
		myModel.addCommandListener((CommandObserver)myUserDefinedPane);
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
