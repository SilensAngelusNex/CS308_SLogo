package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

/**
 * A class used to store colors by index. Observable by Color Observers.
 * @author Weston
 *
 */
public class ColorPalette implements Observable<ColorObserver> {
	List<ColorObserver> myObservers;
	Color myBackground;
	Map<Integer, Color> myColors;

	public ColorPalette() {
		myColors = new HashMap<Integer, Color>();
		myColors.put(0, Color.color(1, 1, 1));
		myColors.put(1, Color.color(0, 0, 0));
		myColors.put(2, Color.color(1, 0, 0));
		myColors.put(3, Color.color(0, 1, 0));
		myColors.put(4, Color.color(0, 0, 1));
		myColors.put(5, Color.color(0, 1, 1));
		myColors.put(6, Color.color(1, 0, 1));
		myColors.put(7, Color.color(1, 1, 0));
		myBackground = myColors.get(0);
		myObservers = new ArrayList<ColorObserver>();
	}

	/**
	 * Sets index's color to be a color with rgb value of (r, g, b)
	 * @param index
	 * @param r
	 * @param g
	 * @param b
	 * @return index
	 */
	public double setIndex(int index, int r, int g, int b) {
		myColors.put(index, Color.color(r, g, b));
		notifyListenersColorChange(index);
		return index;
	}

	/**
	 * Finds the index of the given color toFind
	 * @param toFind
	 * @return index, or -1, if toFind can't be found.
	 */
	public double getIndex(Color toFind) {
		for (Integer index : myColors.keySet()) {
			if (myColors.get(index).equals(toFind)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Gets the color given by index
	 * @param index
	 * @return the color given by index
	 */
	public Color getColor(int index) {
		return myColors.get(index);
	}

	/**
	 * Sets the background color to the color of index
	 * @param index
	 * @return index
	 */
	public double setBackground(int index) {
		myBackground = myColors.get(index);
		notifyListenersBackgroundChange();
		return index;
	}

	@Override
	public void addListener(ColorObserver v) {
		myObservers.add(v);
		for (Integer index : myColors.keySet()) {
			v.colorChange(index, myColors.get(index));
		}
		v.backgroundChange(myBackground);
	}

	private void notifyListenersBackgroundChange() {
		for (ColorObserver o : myObservers) {
			o.backgroundChange(myBackground);
		}
	}

	private void notifyListenersColorChange(int index) {
		for (ColorObserver o : myObservers) {
			o.colorChange(index, myColors.get(index));
		}
	}

	@Override
	public void removeListener(ColorObserver v) {
		myObservers.remove(v);
	}
}