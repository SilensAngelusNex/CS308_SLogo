package View;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import Model.ColorObserver;
import Model.EnclosureObserver;
import Model.LineModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/**
 * @author Owen Chung, Blake Becerra
 */

public class TurtlePane extends Pane implements EnclosureObserver, ColorObserver{
	private final Dimension DEFAULT_SIZE = new Dimension(1000, 750);
	private Map<TurtleView, ImageView> myTurtleImages;
	private Map<LineModel, Line> myLines;
	
	protected TurtlePane(){
		myTurtleImages = new HashMap<TurtleView, ImageView>();
		myLines = new HashMap<LineModel, Line>();
		initPane();
	}
	

	private void initPane() {
		setMinWidth(DEFAULT_SIZE.getWidth() * 0.7);
		setMaxWidth(DEFAULT_SIZE.getWidth() * 0.7);
		setMinHeight(DEFAULT_SIZE.getHeight() / 1.5);
		setMaxHeight(DEFAULT_SIZE.getHeight()/1.5);
		setStyle("-fx-background-color: white");
	}
	
	/**
	 * Purpose: to change the turtles' image
	 * Assumption: The string given is an actual file name
	 * @param image
	 */
	public void changeTurtleImage(String image){
		 for(ImageView turtle: myTurtleImages.values()){
			 turtle.setImage(new Image(getClass().getClassLoader().getResourceAsStream(image)));
		 }
	}
	
	/** 
	 * Purpose: to add a turtle to the display
	 * Assumptions: The turtle will have an actual file in the directory and will not be null 
	 * Parameters: Turtleview t (TurtleView of the turtle to add) 
	 */
	@Override
	public void addTurtle(TurtleView t) {
		ImageView turtle = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(t.getImagePath())));
		turtle.setFitHeight(50);
		turtle.setFitWidth(50);
		myTurtleImages.put(t, turtle);
		turtle.setRotate(t.getCurrentHeading());
		turtle.relocate(getMaxWidth() / 2 - turtle.getBoundsInLocal().getWidth() / 2 + t.getCurrentLocation().getX(), 
						getMaxHeight() /2  - turtle.getBoundsInLocal().getHeight() / 2 - t.getCurrentLocation().getY());
		getChildren().add(turtle);
	}

	/**
	 * Purpose: to remove a turtle from the display
	 * Assumptions: The TurtleView given will not be null and already be in the display
	 * Parameters: TurtleView t (TurtleView to be removed from the display
	 */
	@Override
	public void removeTurtle(TurtleView t) {
		ImageView turtle = myTurtleImages.get(t);
		getChildren().remove(turtle);
	}

	/**
	 * Purpose: To move a certain turtle already on the display
	 * Assumptions: The turtle view will not be null and will already exist in the display
	 * Parameters: TurtleView t 
	 */
	@Override
	public void moveTurtle(TurtleView t) {	
		ImageView turtle = myTurtleImages.get(t);
		turtle.setRotate(t.getCurrentHeading());
		turtle.relocate(getMaxWidth() / 2 - turtle.getBoundsInLocal().getWidth() / 2 + t.getCurrentLocation().getX(), 
				getMaxHeight() /2  - turtle.getBoundsInLocal().getHeight() / 2 - t.getCurrentLocation().getY());
	}

	/**
	 * Purpose: To add a line to the display
	 * Assumptions: the line model won't be null and will have beginning and end points on the display. 
	 * Parameters: LineModel l
	 */
	@Override
	public void addLine(LineModel l) {
		Line lineToAdd = new Line(getMaxWidth() / 2 + l.getStart().getX(), 
				getMaxHeight() / 2 - l.getStart().getY(), 
				getMaxWidth() / 2 + l.getEnd().getX(), 
				getMaxHeight() /2 - l.getEnd().getY());
		lineToAdd.setStroke(l.getColor());
		lineToAdd.setStrokeWidth(l.getWidth());
		myLines.put(l, lineToAdd);
		getChildren().add(lineToAdd);
	}

	/**
	 * Purpose: to remove a line from the display
	 * Assumptions: the line is already in the display
	 * Parameter: LineModel l
	 */
	@Override
	public void removeLine(LineModel l) {	
		getChildren().remove(myLines.get(l));
	}

	
	@Override
	public void colorChange(int index, Color newColor) {
	}
	
	/**
	 *Purpose: To change the background of the display
	 *Assumptions: an actual color is given
	 */
	@Override
	public void backgroundChange(Color newColor) {
		this.setStyle("-fx-background-color: #" + newColor.toString().substring(2, 8) + ";");
	}
}
