package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class MultiArgumentCommand extends AbstractCommandList{	
	private CommandFactory myFactory;

	public MultiArgumentCommand(CommandableModel model, ResourceBundle language, CommandFactory factory) {
		super("(", model, language);
		myFactory = factory;
	}
	
	@Override
	public void endList(){
		endList(")");
	}


	@Override
	protected double execCommand() throws InvalidCommandException {
		throw new UnsupportedOperationException("This");
		/*
		double startVal = getChild(1).execute();
		
		for (int i = 2; i < getChildren().size(); i++){
			Command toExec = CommandFactory.newCommand(getChild(0));
		}
		*/
	}
	
	

	@Override
	protected void execNonTurtleCommand() throws InvalidCommandException {

		int argsPerCmd = getChild(0).maxArgs();
		if (
				((getChildren().size() - 2) % argsPerCmd != 0) ||
				(getChildren().size() < argsPerCmd + 1) ||
				argsPerCmd == 0
			) {
			throw argError();
		}
		
		String cmdType = getChild(0).getName();
		
		int currArg = 1;
		
		Command curr = myFactory.newCommand(cmdType);
		CommandList root = myFactory.newCommandList();
		
		for (int i = 0; i < argsPerCmd; i++){
			curr.addChild(getChild(currArg));
			currArg++;
		}
		
		Command next;
		while (currArg < getChildren().size()){
			next = myFactory.newCommand(cmdType);
			
			if (argsPerCmd > 1)
				next.addChild(curr);
			else
				root.addChild(curr);
			curr = next;

			if (argsPerCmd > 1)
				for (int i = 0; i < argsPerCmd - 1; i++){
					curr.addChild(getChild(currArg));
					currArg++;
				}
			else {
				curr.addChild(getChild(currArg));
				currArg++;
			}
		}
		root.addChild(curr);
		
		selfReplace(root);
		root.endList();
		root.execNonTurtle();
	}
}
