package View;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class ColorElement {
	 private SimpleStringProperty myColor;
	 private SimpleIntegerProperty myIndex;
	 
	public ColorElement(Color variable, int index) {
		myColor = new SimpleStringProperty(variable.toString());
        myIndex = new SimpleIntegerProperty(index);  
	}

	public String getColorString() {
		return myColor.get();
	}
	
	public void setColorString(String color){
		myColor.set(color);
	}


	public int getIndex() {
		return myIndex.get();
	}

	public void setIndex(int index) {
		myIndex.set(index);
	}

	@Override
	public boolean equals(Object o){
			return (this.getIndex() == ((ColorElement)o).getIndex() && 
					this.getColorString().equals(((ColorElement)o).getColorString()));
		
	}
}
