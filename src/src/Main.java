import java.awt.Dimension;

import Controller.SlogoController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Owen Chung
 */

public class Main extends Application {
	public static final String TITLE = "SLOGO - Group 14";
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(TITLE);
		SlogoController myController = new SlogoController();
		Scene primaryscene = new Scene(myController.getView(), DEFAULT_SIZE.width, DEFAULT_SIZE.height);
		primaryStage.setScene(primaryscene);
		primaryStage.show();

	}

	public static void main (String[] args) {
		launch(args);
	}
}