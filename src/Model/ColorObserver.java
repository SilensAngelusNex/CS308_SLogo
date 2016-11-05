package Model;

import javafx.scene.paint.Color;

/**
 * An interface that allows the implementing class to observe the ColorPalette
 * @author Weston
 *
 */
public interface ColorObserver {

	/**
	 * Called when the color of index changes to newColor
	 * @param index
	 * @param newColor
	 */
	abstract public void colorChange(int index, Color newColor);

	/**
	 * Called when the background changes to newColor
	 * @param newColor
	 */
	abstract public void backgroundChange(Color newColor);
}