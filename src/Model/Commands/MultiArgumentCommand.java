package Model.Commands;

import java.util.ResourceBundle;

import Model.CommandableModel;
import parser.InvalidCommandException;

public class MultiArgumentCommand extends AbstractCommandList{
	public static final String myName = "MultiArg";
	

	public MultiArgumentCommand(CommandableModel model, ResourceBundle language) {
		super("(", model, language);
	}
	
	@Override
	public void endList(){
		endList(")");
	}


	@Override
	protected double execCommand() throws InvalidCommandException {
		throw new UnsupportedOperationException();
		/*
		double startVal = getChild(1).execute();
		
		for (int i = 2; i < getChildren().size(); i++){
			Command toExec = CommandFactory.newCommand(getChild(0));
		}
		*/
	}

}
