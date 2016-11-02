package Controller;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.ColorObserver;
import Model.SlogoModel;
import View.SlogoView;
import View.UserDefinedPane;

public class MultipleWorkspace implements MakeMultipleWorkspaceInterface{
	
	private SlogoModel myModel;
	private SlogoView myView;
	private UserDefinedPane myUserDefinedPane = new UserDefinedPane();
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	
	public MultipleWorkspace(String language){
		myView = new SlogoView(language, new UserDefinedPane());
		List<ColorObserver> colorobs = new ArrayList<ColorObserver>();
		colorobs.add((ColorObserver)myView.getTurtlePane());
		colorobs.add((ColorObserver) myView.getHistoryColorPane());
		myModel = new SlogoModel(myView.getTurtlePane(), colorobs, DEFAULT_SIZE.getWidth() * 0.7 / 2, DEFAULT_SIZE.getHeight() / 1.5 / 2);
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
		myView.setConsolePane();
	}

	@Override
	public void makeWorkspace() {
		myView.setModelInViewInterface((ModelInViewInterface) myModel);
		myView.setMakeMultipleWorkspaceInterface(new MultipleWorkspace("english"));
		myModel.addListener(myUserDefinedPane);
		myView.setConsolePane();
		Scene workspaceScene = new Scene(myView, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		Stage workspace = new Stage();
		workspace.setScene(workspaceScene);
		workspace.show();
	}

	
}
