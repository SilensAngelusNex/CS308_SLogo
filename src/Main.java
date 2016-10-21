import View.SlogoView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	 public static final String TITLE = "SLOGO - Group 14";
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle(TITLE);
		SlogoView display = new SlogoView("English");
		primaryStage.setScene(display.getScene());
		primaryStage.show();

	}
	
	public static void main (String[] args) {
	    launch(args);
	}
}
