package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/**
 * @author Owen Chung
 */
public class UserManualPopup extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *Starts the screen with the help page link
     * @param primaryState
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Manual");
        Group myRoot = new Group();
        WebView helppage = new WebView();
		helppage.getEngine().load("http://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php");
        myRoot.getChildren().addAll(helppage);
        primaryStage.setScene(new Scene(myRoot, 800, 400));
        primaryStage.show();
    }

}
