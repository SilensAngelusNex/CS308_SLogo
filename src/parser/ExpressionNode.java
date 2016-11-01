package parser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a node in an expression tree.
 * 
 * @author Daniel Chai
 */
public class ExpressionNode {
	private String command;
	private List<ExpressionNode> children;

	public ExpressionNode() {
		this.command = "";
		this.children = new ArrayList<ExpressionNode>();
	}

	public ExpressionNode(String command) {
		this.command = command;
		this.children = new ArrayList<ExpressionNode>();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<ExpressionNode> getChildren() {
		return children;
	}

	public ExpressionNode getChild(int index) {
		if (index < 0 || index >= children.size()) {
			throw new IndexOutOfBoundsException();
		}
		return children.get(index);
	}

	public void addChild(ExpressionNode child) {
		children.add(child);
	}

	public void setChild(int index, ExpressionNode child) {
		children.set(index, child);
	}

	public int getNumOfChildren() {
		return children.size();
	}
}
