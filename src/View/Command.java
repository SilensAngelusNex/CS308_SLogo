package View;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Command {
	 private SimpleStringProperty myCommand;
	 private SimpleIntegerProperty myNumberOfArguments;
	 
	 public Command(String command, int numargs) {
			myCommand = new SimpleStringProperty(command);
			myNumberOfArguments = new SimpleIntegerProperty(numargs);  
		}

		public String getCommandString() {
			return myCommand.get();
		}
		
		public void setCommand(String variable){
			myCommand.set(variable);
		}


		public int getNumofArguments() {
			return myNumberOfArguments.get();
		}

		public void setNumofArguments(int value) {
			myNumberOfArguments.set(value);
		}

		@Override
		public boolean equals(Object o){
				return (this.getNumofArguments() == ((Command)o).getNumofArguments() && 
						this.getCommandString().equals(((Command)o).getCommandString()));
			
		}
	 
}
