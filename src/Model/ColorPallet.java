package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class ColorPallet implements Observable<ColorObserver>{
	List<ColorObserver> myObservers;
	Color myBackground;
	List<Color> myColors;
	
	public ColorPallet(){
		myColors = new ArrayList<Color>();
		myBackground = Color.color(0, 0, 0);
	}
	
	public void addColor(int r, int g, int b){
		myColors.add(Color.color(r, g, b));
	}

	public double setIndex(int index, int r, int g, int b) {
		myColors.set(index, Color.color(r, g, b));
		notifyListenersColorChange(index);
		return index;
	}
	
	public double getIndex(Color toFind){
		int i = 0;
		while (i < myColors.size()){
			if (myColors.get(i).equals(toFind))
				break;
			i++;
		}
		return i;
	}
	
	public Color getColor(int index){
		return myColors.get(index);
	}

	public void setBackground(int index) {
		myBackground = myColors.get(index);
		notifyListenersBackgroundChange();
	}

	@Override
	public void addListener(ColorObserver v) {
		myObservers.add(v);
		for (int i = 0; i < myColors.size(); i++)
			v.colorChange(i, myColors.get(i));
		v.backgroundChange(myBackground);
	}

	private void notifyListenersBackgroundChange() {
		for (ColorObserver o: myObservers){
			o.backgroundChange(myBackground);
		}
	}

	private void notifyListenersColorChange(int index) {
		for (ColorObserver o: myObservers){
			o.colorChange(index, myColors.get(index));
		}
	}

	@Override
	public void removeListener(ColorObserver v) {
		myObservers.remove(v);
	}
}
