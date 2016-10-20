import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import user_interface.SlogoView;

public class Main extends Application {
	 public static final String TITLE = "SLOGO - Group 14";
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle(TITLE);
		SlogoView display = new SlogoView("English");
		primaryStage.setScene(display.getScene());
		primaryStage.show();
		
		//display.showPage(DEFAULT_START_PAGE);

	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}
