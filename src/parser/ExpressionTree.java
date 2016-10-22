package parser;

/**
 * This is a wrapper class for an expression tree.
 * 
 * @author Daniel Chai
 */
public class ExpressionTree {
	private ExpressionNode root;
	
	public ExpressionTree(ExpressionNode root) {
		this.root = root;
	}

	public ExpressionNode getRoot() {
		return root;
	}
}
