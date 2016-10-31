package Model.Commands;

import java.util.Map;
import java.util.ResourceBundle;

import Model.Commands.TurtleCommands.*;
import parser.Language;
import Model.Commands.MathCommands.*;
import Model.CommandableModel;
import Model.Commands.ControlCommands.*;
import Model.Commands.DisplayCommands.*;

public class CommandFactory {
	Map<String, UserDefinedCommand> myUserDefinedCommands;
	CommandableModel myModel;
	ResourceBundle mySyntax;
	ResourceBundle myCommands;
	
	public CommandFactory(String syntaxPath, Language language, CommandableModel model) {
		myModel = model;
		mySyntax = ResourceBundle.getBundle(syntaxPath);
		myCommands = language.getResource();
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
				throw new UnsupportedOperationException("Can't assign user commands.");
				//return new ToCommand();
			
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
				
			default:
				if (myUserDefinedCommands.containsKey(command))
					return myUserDefinedCommands.get(command);
				else
					throw new UnsupportedOperationException("Unimplemented Command: " + command);
			}
		} else if (command.matches(mySyntax.getString("Variable"))) {
			return new VariableCommand(myModel, myCommands, command);
		} else if (command.matches(mySyntax.getString("Constant"))) {
			return new ConstantCommand(myModel, myCommands, command);
		} else
			return null;
	}

	public CommandList newCommandList() {
		return new CommandList(myModel, myCommands);
	}
	public MultiArgumentCommand newCommandGroup() {
		return new MultiArgumentCommand(myModel, myCommands);
	}
}
