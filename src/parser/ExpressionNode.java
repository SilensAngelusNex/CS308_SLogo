package parser;

/**
 * This class represents a node in an expression tree.
 * 
 * @author Daniel Chai
 */
public class ExpressionNode {
	private String command;
	private Double value;
	private ExpressionNode child1;
	private ExpressionNode child2;
	
	public ExpressionNode(String command) {
		this.command = command;
		this.value = null;
		this.child1 = null;
		this.child2 = null;
	}
	
	public ExpressionNode(String command, ExpressionNode child1) {
		this.command = command;
		this.value = null;
		this.child1 = child1;
		this.child2 = null;
	}
	
	public ExpressionNode(String command, ExpressionNode child1, ExpressionNode child2) {
		this.command = command;
		this.value = null;
		this.child1 = child1;
		this.child2 = child2;
	}
	
	public ExpressionNode(Double value) {
		this.command = null;
		this.value = value;
		this.child1 = null;
		this.child2 = null;
	}
	
	public String getCommand() {
		return command;
	}
	
	public Double getValue() {
		return value;
	}
	
	public ExpressionNode getChild1() {
		return child1;
	}
	
	public ExpressionNode getChild2() {
		return child2;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public void setChild1(ExpressionNode child1) {
		this.child1 = child1;
	}
	
	public void setChild2(ExpressionNode child2) {
		this.child2 = child2;
	}
}
