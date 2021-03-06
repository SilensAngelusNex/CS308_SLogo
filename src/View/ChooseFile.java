package View;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/* 
 * based of code used in my groups cell Society (Blake)
 * edited by Owen Chung (tc153)
 * Purpose: to open a new screen so the user can choose a file
 */
public class ChooseFile {

	private Stage myStage;
	
	public ChooseFile(){
		myStage = new Stage();
	}

	/**
	 * Purpse: the method to actually choose a file
	 * @return the file chosen 
	 */
	public File chooseFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Images (*.png, *jpg)", "*.png", "*.jpg");
		String userDirectoryString = System.getProperty("user.dir");
		File userDirectory = new File(userDirectoryString);
		fileChooser.setInitialDirectory(userDirectory);
		fileChooser.getExtensionFilters().add(extentionFilter);
		File chosenFile = fileChooser.showOpenDialog(myStage);
		if (chosenFile == null) {
			myStage.close();
			return null;
		}
		return chosenFile;
	}

}
