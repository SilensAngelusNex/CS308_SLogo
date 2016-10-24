package View;


import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Console {
	private TextFlow myPanel;
	private int linelimit = 11;
	public Console(){
		Text consolelabel = new Text("Console\n");
		consolelabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
		myPanel = new TextFlow(consolelabel);
		myPanel.setStyle("-fx-background-color: white");
		myPanel.setPrefSize(400, 200);
	}

	protected TextFlow getPanel() {
		return myPanel;
	}
	
	protected void addTexttoConsole(Text t){
		if (myPanel.getChildren().size() == linelimit){
			myPanel.getChildren().remove(1);
		}
		myPanel.getChildren().add(t);
	}
}
