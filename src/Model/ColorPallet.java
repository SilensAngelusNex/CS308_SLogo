package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorPallet {
	Color myBackground;
	List<Color> myColors;
	
	public ColorPallet(){
		myColors = new ArrayList<Color>();
		myBackground = new Color(0, 0, 0);
	}
	
	public void addColor(int r, int g, int b){
		myColors.add(new Color(r, g, b));
	}

	public double setIndex(int index, int r, int g, int b) {
		myColors.set(index, new Color(r, g, b));
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
		
	}
}
