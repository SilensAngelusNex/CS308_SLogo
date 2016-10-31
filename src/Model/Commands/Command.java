package Model.Commands;

import java.util.List;

import parser.InvalidCommandException;

public interface Command {
		
	abstract public double execute() throws InvalidCommandException;
	abstract public void addChild(Command cmd);
	abstract public boolean argsNotFull();
	abstract public int maxArgs();
	abstract public String toString();
	abstract public String getName();
	abstract public List<Command> getChildren();
	abstract public Command getChild(int index);
	abstract public Command getParent();
	abstract public void setParent(Command cmd);
}
