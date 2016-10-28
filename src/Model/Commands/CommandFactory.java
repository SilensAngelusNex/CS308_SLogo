package Model.Commands;

import java.util.Map;

import Model.Commands.TurtleCommands.*;
import Model.Commands.MathCommands.*;
import Model.Commands.ControlCommands.*;

public class CommandFactory {
	Map<String, UserDefinedCommand> myUserDefinedCommands;
	

	public Command newCommand(String command){
		switch (command){
		case "Forward":
			return new ForwardCommand();
		case "Backward":
			return new BackCommand();
		case "Left":
			return new LeftCommand();
		case "Right":
			return new RightCommand();
		case "SetHeading":
			return new SetHeadingCommand();
		case "SetTowards":
			return new TowardCommand();
		case "SetPosition":
			return new GoToCommand();
		case "PenDown":
			return new PenDownCommand();
		case "PenUp":
			return new PenUpCommand();
		case "ShowTurtle":
			return new ShowTurtleCommand();
		case "HideTurtle":
			return new HideTurtleCommand();
		case "Home":
			return new HomeCommand();
		case "ClearScreen":
			return new ClearScreenCommand();
			
		case "XCoordinate":
			return new XCoorCommand();
		case "YCoordinate":
			return new YCoorCommand();
		case "Heading":
			return new HeadingCommand();
		case "IsPenDown":
			return new IsPenDownCommand();
		case "IsShowing":
			return new IsShowingCommand();
			
		case "Sum":
			return new SumCommand();
		case "Difference":
			return new DifferenceCommand();
		case "Product":
			return new ProductCommand();
		case "Quotient":
			return new QuotientCommand();
		case "Remainder":
			return new ForwardCommand();
		case "Minus":
			return new ForwardCommand();
		case "Random":
			return new ForwardCommand();
		case "Sine":
			return new ForwardCommand();
		case "Cosine":
			return new ForwardCommand();
		case "Tangent":
			return new ForwardCommand();
		case "ArcTangent":
			return new ForwardCommand();
		case "NaturalLog":
			return new ForwardCommand();
		case "Power":
			return new ForwardCommand();
		case "Pi":
			return new ForwardCommand();
			
		case "LessThan":
			return new LessCommand();
		case "GreaterThan":
			return new GreaterCommand();
		case "Equal":
			return new EqualCommand();
		case "NotEqual":
			return new NotEqualCommand();
		case "And":
			return new AndCommand();
		case "Or":
			return new OrCommand();
		case "Not":
			return new NotCommand();
			
		case "MakeVariable":
			return new MakeCommand();
		case "Repeat":
			return new RepeatCommand();
		case "DoTimes":
			return new DoTimesCommand();
		case "For":
			return new ForCommand();
		case "If":
			return new IfCommand();
		case "IfElse":
			return new IfElseCommand();
		case "MakeUserInstruction":
			throw new UnsupportedOperationException("Can't assign user commands.");
			//return new ToCommand();
			
		default:
			throw new UnsupportedOperationException("Unimplemented Command: " + command);
		}
		}
}
