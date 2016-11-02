package Model.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.util.Pair;

import parser.Language;
import Model.CommandObserver;
import Model.CommandableModel;
import Model.Observable;
import Model.Commands.TurtleCommands.*;
import Model.Commands.MathCommands.*;
import Model.Commands.ControlCommands.*;
import Model.Commands.DisplayCommands.*;

public class CommandFactory implements Observable<CommandObserver> {
	private Map<String, Pair<List<String>, Command>> myUserDefinedCommands;
	private CommandableModel myModel;
	private ResourceBundle mySyntax;
	private ResourceBundle myCommands;
	private List<CommandObserver> myObservers;
	
	public CommandFactory(String syntaxPath, Language language, CommandableModel model) {
		myModel = model;
		mySyntax = ResourceBundle.getBundle(syntaxPath);
		myCommands = language.getResource();
		myUserDefinedCommands = new HashMap<String, Pair<List<String>, Command>>();
		myObservers = new ArrayList<CommandObserver>();
	}
	
	public Command newCommand(Command c) {
		return newCommand(c.getName());
	}
	
	public Command newCommand(double val) {
		return new ConstantCommand(myModel, myCommands, Double.toString(val));
	}

	public Command newCommand(String command){
		if (command.matches(mySyntax.getString("Command"))) {
			switch (command){
			//Turtle Commands
			case "Forward":
				return new ForwardCommand(myModel, myCommands);
			case "Backward":
				return new BackCommand(myModel, myCommands);
			case "Left":
				return new LeftCommand(myModel, myCommands);
			case "Right":
				return new RightCommand(myModel, myCommands);
			case "SetHeading":
				return new SetHeadingCommand(myModel, myCommands);
			case "SetTowards":
				return new TowardCommand(myModel, myCommands);
			case "SetPosition":
				return new GoToCommand(myModel, myCommands);
			case "PenDown":
				return new PenDownCommand(myModel, myCommands);
			case "PenUp":
				return new PenUpCommand(myModel, myCommands);
			case "ShowTurtle":
				return new ShowTurtleCommand(myModel, myCommands);
			case "HideTurtle":
				return new HideTurtleCommand(myModel, myCommands);
			case "Home":
				return new HomeCommand(myModel, myCommands);
			case "ClearScreen":
				return new ClearScreenCommand(myModel, myCommands);
			
			//Turtle Queries
			case "XCoordinate":
				return new XCoorCommand(myModel, myCommands);
			case "YCoordinate":
				return new YCoorCommand(myModel, myCommands);
			case "Heading":
				return new HeadingCommand(myModel, myCommands);
			case "IsPenDown":
				return new IsPenDownCommand(myModel, myCommands);
			case "IsShowing":
				return new IsShowingCommand(myModel, myCommands);
			
			//Math Commands
			case "Sum":
				return new SumCommand(myCommands);
			case "Difference":
				return new DifferenceCommand(myCommands);
			case "Product":
				return new ProductCommand(myCommands);
			case "Quotient":
				return new QuotientCommand(myCommands);
			case "Remainder":
				return new RemainderCommand(myCommands);
			case "Minus":
				return new MinusCommand(myCommands);
			case "Random":
				return new RandomCommand(myCommands);
			case "Sine":
				return new SineCommand(myCommands);
			case "Cosine":
				return new CosineCommand(myCommands);
			case "Tangent":
				return new TangentCommand(myCommands);
			case "ArcTangent":
				return new ArcTangentCommand(myCommands);
			case "NaturalLog":
				return new LogCommand(myCommands);
			case "Power":
				return new PowerCommand(myCommands);
			case "Pi":
				return new PiCommand(myCommands);
				
			//Boolean Commands
			case "LessThan":
				return new LessCommand(myCommands);
			case "GreaterThan":
				return new GreaterCommand(myCommands);
			case "Equal":
				return new EqualCommand(myCommands);
			case "NotEqual":
				return new NotEqualCommand(myCommands);
			case "And":
				return new AndCommand(myCommands);
			case "Or":
				return new OrCommand(myCommands);
			case "Not":
				return new NotCommand(myCommands);
			
			//Control Commands
			case "MakeVariable":
				return new MakeCommand(myModel, myCommands);
			case "Repeat":
				return new RepeatCommand(myModel, myCommands);
			case "DoTimes":
				return new DoTimesCommand(myModel, myCommands);
			case "For":
				return new ForCommand(myModel, myCommands);
			case "If":
				return new IfCommand(myModel, myCommands);
			case "IfElse":
				return new IfElseCommand(myModel, myCommands);
			case "MakeUserInstruction":
				return new ToCommand(myModel, myCommands, this);
			
			//Display Commands
			case "SetBackground":
				return new SetBackgroundCommand(myModel, myCommands);
			case "SetPenColor": 
				return new SetPenColorCommand(myModel, myCommands);
			case "SetPenSize":
				return new SetPenSizeCommand(myModel, myCommands);
			case "SetShape":
				return new SetShapeCommand(myModel, myCommands);
			case "SetPalette":
				return new SetPaletteCommand(myModel, myCommands);
			case "GetPenColor":
				return new PenColorCommand(myModel, myCommands);
			case "GetShape":
				return new ShapeCommand(myModel, myCommands);
			
			//User Defined Commands
			default:
				if (myUserDefinedCommands.containsKey(command.toLowerCase()))
					return new UserDefinedCommand(
							myModel,
							myCommands,
							command,
							this
							);
				else
					return new UserDefinedCommand(
							myModel,
							myCommands,
							command
							);
			}
		} else if (command.matches(mySyntax.getString("Variable"))) {
			return new VariableCommand(myModel, myCommands, command.replace(":", ""));
		} else if (command.matches(mySyntax.getString("Constant"))) {
			return new ConstantCommand(myModel, myCommands, command);
		} else
			return null;
	}

	public CommandList newCommandList() {
		return new CommandList(myModel, myCommands);
	}
	public MultiArgumentCommand newCommandGroup() {
		return new MultiArgumentCommand(myModel, myCommands, this);
	}

	public double addUserCommand(String commandName, List<String> argNames, Command ops) {
		if (!myUserDefinedCommands.containsKey(commandName.toLowerCase())) {
			return 0;
		}
		if (executable(ops)) {
			notifyListenersAddCommand(commandName.toLowerCase(), argNames.size());
			myUserDefinedCommands.put(commandName.toLowerCase(), new Pair<List<String>, Command>(argNames, ops));
			return 1;
		} else {
			myUserDefinedCommands.remove(commandName.toLowerCase());
			return 0;
		}
	}

	public void tentativeAddUserCommand(String commandName, ArrayList<String> argNames) {
		myUserDefinedCommands.put(commandName.toLowerCase(), new Pair<List<String>, Command>(argNames, null));
	}
	
	private boolean executable(Command c){
		boolean result = !c.argsNotFull();
		
		for (Command child: c.getChildren()){
			if (!result)
				break;
			result = (result && executable(child));
		}
		
		return result;
			
	}

	public List<String> getUserArgs(String myName) {
		return myUserDefinedCommands.get(myName).getKey();
	}

	public Command getUserCommand(String myName) {
		return myUserDefinedCommands.get(myName).getValue();
	}

	@Override
	public void addListener(CommandObserver v) {
		myObservers.add(v);
	}

	@Override
	public void removeListener(CommandObserver v) {
		myObservers.remove(v);
	}
	
	private void notifyListenersAddCommand(String commandName, int numArgs) {
		for (CommandObserver o : myObservers) {
			o.addCommand(commandName, numArgs);
		}
	}
}
