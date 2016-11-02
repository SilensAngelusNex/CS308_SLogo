package Model.Commands;

import java.util.List;

import Model.TurtleModel;
import parser.InvalidCommandException;

public interface Command {
		
	abstract public double execute(TurtleModel t) throws InvalidCommandException;
	abstract public void execNonTurtle(TurtleModel t) throws InvalidCommandException;
	abstract public boolean isTurtleCommand();
	abstract public boolean isVariableCommand();
	abstract public void addChild(Command cmd);
	abstract public void selfReplace(Command replacement);
	abstract public boolean argsNotFull();
	abstract public int maxArgs();
	abstract public String toString();
	abstract public String getName();
	abstract public List<Command> getChildren();
	abstract public Command getChild(int index);
	abstract public Command getParent();
	abstract public void setParent(Command cmd);
	abstract public void setTurtle(TurtleModel t);
	abstract public TurtleModel getTurtle();
	public abstract void setTurtleRecursive(TurtleModel value);
}
