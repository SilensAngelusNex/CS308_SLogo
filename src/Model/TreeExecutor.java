package Model;

import java.util.ResourceBundle;

import parser.ExpressionNode;
import parser.ExpressionTree;

public class TreeExecutor {
	String myLanguageFilePath;
	String mySyntaxFilePath;
	
	ResourceBundle myCommands;
	ResourceBundle mySyntax;

	public TreeExecutor(String commands, String syntax){
		mySyntaxFilePath = syntax;
		
		myCommands = ResourceBundle.getBundle(commands);
		mySyntax = ResourceBundle.getBundle(syntax);
	}
	
	public double[] exec(ExpressionTree e, SlogoModel model) {
		int children = e.getRoot().getChildren().size();
		double[] result = new double[children];
		
		for (int i = 0; i < children; i++){
			result[i] = exec(e.getRoot().getChild(i), model);
		}
		
		return result;
	}
	
	private double exec(ExpressionNode node, SlogoModel model) {
		String cmd = node.getCommand();
		
		if (isA(cmd, "Constant")){
			return Double.parseDouble(cmd);
		} else if (isA(cmd, "Variable")){
			return model.get(cmd.replaceAll(":", ""));
		} else if (isA(cmd, "Command")){
			return execCommand(node, model);
		} else if (cmd.equals("")){
			double result = 0;
			for (ExpressionNode n: node.getChildren()){
				result = exec(n, model);
			}
			return result;
		} else {
			throw new UnsupportedOperationException(String.format("Only Commands, Variables, and Constants can be evaluated. \"%s\" is none of those.", cmd));
		}
			
	}
	
	private double execCommand(ExpressionNode node, SlogoModel model){
		try {
	
			switch (node.getCommand()){
			case "Forward":
				return model.forward(exec(node.getChild(0), model));
			case "Backward":
				return model.back(exec(node.getChild(0), model));
			case "Left":
				return model.left(exec(node.getChild(0), model));
			case "Right":
				return model.right(exec(node.getChild(0), model));
			case "SetHeading":
				return model.setHeading(exec(node.getChild(0), model));
			case "SetTowards":
				return model.towards(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "SetPosition":
				return model.goTo(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "PenDown":
				return model.penDown();
			case "PenUp":
				return model.penUp();
			case "ShowTurtle":
				return model.showTurtle();
			case "HideTurtle":
				return model.hideTurtle();
			case "Home":
				return model.home();
			case "ClearScreen":
				return model.clearScreen();
				
			case "XCoordinate":
				return model.xCor();
			case "YCoordinate":
				return model.yCor();
			case "Heading":
				return model.heading();
			case "IsPenDown":
				return model.isPenDown();
			case "IsShowing":
				return model.isShowing();
				
			case "Sum":
				return model.sum(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Difference":
				return model.difference(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Product":
				return model.product(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Quotient":
				return model.quotient(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Remainder":
				return model.remainder(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Minus":
				return model.minus(exec(node.getChild(0), model));
			case "Random":
				return model.random(exec(node.getChild(0), model));
			case "Sine":
				return model.sin(exec(node.getChild(0), model));
			case "Cosine":
				return model.cos(exec(node.getChild(0), model));
			case "Tangent":
				return model.tan(exec(node.getChild(0), model));
			case "ArcTangent":
				return model.atan(exec(node.getChild(0), model));
			case "NaturalLog":
				return model.log(exec(node.getChild(0), model));
			case "Power":
				return model.pow(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Pi":
				return model.pi();
				
			case "LessThan":
				return model.less(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "GreaterThan":
				return model.greater(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Equal":
				return model.equal(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "NotEqual":
				return model.notEqual(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "And":
				return model.and(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Or":
				return model.or(exec(node.getChild(0), model), exec(node.getChild(1), model));
			case "Not":
				return model.not(exec(node.getChild(0), model));
				
			case "MakeVariable":
				return makeVariable(node, model);
			case "Repeat":
				return repeat(node, model);
			case "DoTimes":
				return doTimes(node, model);
			case "For":
				return forLoop(node, model);
			case "If":
				return ifStatement(node, model);
			case "IfElse":
				return ifElse(node, model);
			case "MakeUserInstruction":
				return makeUserInstruction(node, model);
				
			default:
				throw new UnsupportedOperationException("Unimplemented Command: " + node.getCommand());
				
				
			}
			
		} catch (NullPointerException e){
			String errorCommand;
			if (myCommands.getString(node.getCommand()) != null)
				errorCommand = myCommands.getString(node.getCommand());
			else
				errorCommand = node.getCommand();
			throw new NullPointerException(String.format("%s%s", errorCommand, myCommands.getString("ArgError")));
		}
	}


	private boolean isA(String command, String type){
		return command.matches(mySyntax.getString(type));
	}
	
	
	private double repeat(ExpressionNode node, SlogoModel model) {
		double result = 0;
		
		int repeatTimes = (int) exec(node.getChild(0), model); 
		for (int i = 0; i < repeatTimes; i++){
			result = exec(node.getChild(1), model);
		}
		
		return result;
	}
	
	private double doTimes(ExpressionNode node, SlogoModel model) {

		String varName = node.getChild(0).getChild(0).getCommand().replaceAll(":", "");
		int times = (int) exec(node.getChild(0).getChild(1), model);
		ExpressionNode toExec = node.getChild(1);
		
		model.set(varName, 0);
		
		double result = 0;
		for (int i = 0; i < times; i++){
			result = exec(toExec, model);
		}
		
		return result;
	}
	
	private double forLoop(ExpressionNode node, SlogoModel model) {
		String varName = node.getChild(0).getChild(0).getCommand().replaceAll(":", "");
		int start = (int) exec(node.getChild(0).getChild(1), model);
		int end = (int) exec(node.getChild(0).getChild(2), model);
		int jump = (int) exec(node.getChild(0).getChild(3), model);
		
		ExpressionNode toExec = node.getChild(1);
		
		model.set(varName, start);
		
		double result = 0;
		while (model.get(varName) < end){
			result = exec(toExec, model);
			model.set(varName, model.get(varName) + jump);
		}
		
		return result;
	}
	
	private double ifStatement(ExpressionNode node, SlogoModel model){
		if (exec(node.getChild(0), model) != 0){
			return exec(node.getChild(1), model);
		} else {
			return 0;
		}
	}


	private double ifElse(ExpressionNode node, SlogoModel model) {
		if (exec(node.getChild(0), model) != 0){
			return exec(node.getChild(1), model);
		} else {
			return exec(node.getChild(2), model);
		}
		
	}
	
	private double makeVariable(ExpressionNode node, SlogoModel model) {
		String varName = node.getChild(0).getCommand().replaceAll(":", "");
		return model.set(varName, exec(node.getChild(1), model));
	}
	
	private double makeUserInstruction(ExpressionNode node, SlogoModel model) {
		throw new UnsupportedOperationException("Stop making instructions. Just stop.");
		
	}


}
