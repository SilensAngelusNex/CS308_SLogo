package View;

import java.awt.Dimension;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controller.ModelInViewInterface;
import Model.SlogoModel;

public class MultipleWorkspace {
	
	private SlogoModel myModel;
	private SlogoView myView;
	
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	
	public MultipleWorkspace(){
		myView = new SlogoView("english", new UserDefinedPane());
		myModel = new SlogoModel(myView.getTurtlePane(), DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
		myView.setConsolePane();
	}

	public void makeWorkspace(){
		Stage newWorkspace = new Stage();
		newWorkspace.setScene(new Scene(myView, DEFAULT_SIZE.width, DEFAULT_SIZE.height));
		newWorkspace.show();
	}
	
	public Pane getMyView(){
		return myView;
	}
}
