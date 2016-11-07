package View;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

/**
 * @author Owen Chung
 */
public class ColorElement {
	private SimpleStringProperty myColor;
	private SimpleIntegerProperty myIndex;

	public ColorElement(Color variable, int index) {
		myColor = new SimpleStringProperty(variable.toString());
		myIndex = new SimpleIntegerProperty(index);  
	}

	/**
	 *Purpose: to get the string name of the color element
	 * @return myColor.get()
	 */
	public String getColorString() {
		return myColor.get();
	}

	/**
	 * Purpose: To set the color element
	 * @param color
	 */
	public void setColorString(String color){
		myColor.set(color);
	}

	/**
	 * Purpose: to get the index of the color
	 * @return myIndex.get()
	 */
	public int getIndex() {
		return myIndex.get();
	}

	/**
	 *Purpose: To set the index of the color
	 * @param index
	 */
	public void setIndex(int index) {
		myIndex.set(index);
	}
	
	/**
	 * Purpose: to determine of the given object and the current color are the same or not
	 * Assumptions: That the given object can be cast as a ColorElement
	 * @return boolean if the are equal or not
	 */
	@Override
	public boolean equals(Object o){
		return (this.getIndex() == ((ColorElement)o).getIndex() && 
				this.getColorString().equals(((ColorElement)o).getColorString()));

	}
}
