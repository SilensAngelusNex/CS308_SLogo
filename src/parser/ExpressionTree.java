package parser;

import java.util.LinkedList;
import java.util.Queue;

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
	
	/**
	 * Prints the tree in level-order traversal for testing purposes.
	 */
	public void printTree() {
		if (root == null) {
			return;
		}
		
		Queue<ExpressionNode> q = new LinkedList<ExpressionNode>();
		q.add(root);
		
		while (!q.isEmpty()) {
			int numInLevel = q.size();
			
			for (int i = 0; i < numInLevel; i++) {
				ExpressionNode curr = q.poll();
				System.out.print(curr.getCommand() + " ");
				
				for (ExpressionNode child : curr.getChildren()) {
					q.add(child);
				}
			}
			
			System.out.println();
		}
	}
}
