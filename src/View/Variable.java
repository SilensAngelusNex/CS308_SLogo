package View;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * @author Owen Chung
 */
public class Variable {
	 private SimpleStringProperty myVariable;
	 private SimpleDoubleProperty myValue;
	 
	public Variable(String variable, double value) {
		myVariable = new SimpleStringProperty(variable);
        myValue = new SimpleDoubleProperty(value);  
	}

	public String getVariableString() {
		return myVariable.get();
	}
	
	public void setVariable(String variable){
		myVariable.set(variable);
	}


	public double getValue() {
		return myValue.get();
	}

	public void setValue(double value) {
		myValue.set(value);
	}

	@Override
	public boolean equals(Object o){
			return (this.getValue() == ((Variable)o).getValue() && 
					this.getVariableString().equals(((Variable)o).getVariableString()));
		
	}
}
