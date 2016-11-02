package Model.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.CommandableModel;
import Model.TurtleModel;
import Model.Commands.TurtleCommands.AbstractTurtleCommand;
import parser.InvalidCommandException;


abstract public class AbstractCommand implements Command{
	public static final String ERROR_RESOURCE = "resources/CommandError";
	public static final String ERROR_KEY = "NotEnoughArgs";
	
	private List<Command> myChildren;
	private Command myParent;
	private CommandableModel myModel;
	private ResourceBundle myLanguage;
	private TurtleModel myTurtle;
	
	private String argErrorMessage;
	
	protected AbstractCommand(AbstractCommand cmd){
		myLanguage = cmd.myLanguage;
		myModel = cmd.myModel;
		myChildren = new ArrayList<Command>();
		
		initErrorMessage();
	}
	
	public AbstractCommand(CommandableModel model, ResourceBundle language){
		myLanguage = language;
		myModel = model;
		myChildren = new ArrayList<Command>();
		
		initErrorMessage();
	}
	
	private void initErrorMessage() {
		argErrorMessage = ResourceBundle.getBundle(ERROR_RESOURCE).getString(ERROR_KEY);
	}
	
	protected CommandableModel getModel(){
		return myModel;
	}
	
	@Override
	public double execute(TurtleModel t) throws InvalidCommandException {
		if (argsNotFull())
			throw argError();
		else {
			myTurtle = t;
			return execCommand();
		}
	}

	
	@Override
	public void execNonTurtle(TurtleModel t) throws InvalidCommandException {
		if (argsNotFull())
			throw argError();
		else {
			myTurtle = t;
			execNonTurtleCommand();			
		}
	}
	
	@Override
	public boolean isTurtleCommand(){
		boolean children = false;
		for (Command child: myChildren){
			children = children || child.isTurtleCommand();
		}
		return (this instanceof AbstractTurtleCommand) || children;
	}
	
	@Override
	public boolean isVariableCommand(){
		boolean children = false;
		for (Command child: myChildren){
			children = children || child.isVariableCommand();
		}
		return (this instanceof VariableCommand) || children;
	}

	@Override
	public void addChild(Command cmd) {
		if (argsNotFull()) {
			myChildren.add(cmd);
			cmd.setParent(this);
		}
	}
	
	@Override
	public void selfReplace(Command replacement) {
		myParent.getChildren().set(myParent.getChildren().indexOf(this), replacement);
		replacement.setParent(myParent);
		myParent = null;
	}
	
	@Override
	public boolean argsNotFull() {
		return myChildren.size() < maxArgs() || maxArgs() < 0;
	}

	@Override
	public Command getChild(int index) {
		return myChildren.get(index);
	}

	@Override
	public List<Command> getChildren() {
		return myChildren;
	}
	
	@Override
	public void setParent(Command cmd) {
		myParent = cmd;
	}
	
	@Override
	public Command getParent() {
		return myParent;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append("(");
		for (Command c: myChildren)
			s.append(c.toString());
		s.append(")");
		return s.toString();
	}
	
	
	abstract protected double execCommand() throws InvalidCommandException;
	
	protected void execNonTurtleCommand() throws InvalidCommandException {
		if(!isTurtleCommand()){
			execChildrenAndReplaceSelf(myTurtle);
		} else {
			preExecChildren(myTurtle);
		}
		
	}
	
	protected void execChildrenAndReplaceSelf(TurtleModel t) throws InvalidCommandException{
		if (preExecChildren(t)){
			double result = execute(null);	
			selfReplace(new ConstantCommand(this, result));
		}
	}
	
	protected boolean preExecChildren(TurtleModel t) throws InvalidCommandException{
		boolean childrenConstant = true;
		for (int i = 0; i < getChildren().size(); i++){
			getChild(i).execNonTurtle(t);
			childrenConstant = childrenConstant && (getChild(i) instanceof ConstantCommand);
		}
		return childrenConstant;
	}
	
	protected TurtleModel getTurtle(){
		return myTurtle;
	}
	public void setTurtle(TurtleModel t){
		myTurtle = t;
	}
	
	protected InvalidCommandException argError() {
		return new InvalidCommandException(argErrorMessage + getName());
	}
}
