package Controller;

import java.awt.Dimension;

import Model.SlogoModel;
import View.SlogoView;
//import View.StartScreen;
/**
 * @author Owen Chung
 */
public class SlogoController {
	private SlogoModel myModel;
	private SlogoView myView;
	
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	
	public SlogoController(){
		
		myView = new SlogoView("english");
		myModel = new SlogoModel(myView.getTurtlePane(), DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
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
