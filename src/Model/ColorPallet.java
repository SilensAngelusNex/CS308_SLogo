package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class ColorPallet implements Observable<ColorObserver> {
	List<ColorObserver> myObservers;
	Color myBackground;
	Map<Integer, Color> myColors;

	public ColorPallet() {
		myColors = new HashMap<Integer, Color>();
		myBackground = Color.color(1, 1, 1);
		myObservers = new ArrayList<ColorObserver>();
	}

	public void addColor(int r, int g, int b) {
		myColors.put(myColors.size(), Color.color(r, g, b));
	}

	public double setIndex(int index, int r, int g, int b) {
		myColors.put(index, Color.color(r, g, b));
		notifyListenersColorChange(index);
		return index;
	}

	public double getIndex(Color toFind) {
		for (Integer index : myColors.keySet()) {
			if (myColors.get(index).equals(toFind)) {
				return index;
			}
		}
		return -1;
	}

	public Color getColor(int index) {
		return myColors.get(index);
	}

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
