package Model;

import javafx.scene.paint.Color;

public interface ColorObserver {
	
	abstract public void colorChange(int index, Color newColor);
	
	abstract public void backgroundChange(Color newColor);

}
