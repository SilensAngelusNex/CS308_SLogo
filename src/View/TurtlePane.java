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
//TODO: IMAGEVIEW ALLOWED?
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
	
	public void changeTurtleImage(String image){
		 for(ImageView turtle: myTurtleImages.values()){
			 turtle.setImage(new Image(getClass().getClassLoader().getResourceAsStream(image)));
		 }
	}
	
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

	@Override
	public void removeTurtle(TurtleView t) {
		ImageView turtle = myTurtleImages.get(t);
		getChildren().remove(turtle);
	}

	@Override
	public void moveTurtle(TurtleView t) {	
		ImageView turtle = myTurtleImages.get(t);
		turtle.setRotate(t.getCurrentHeading());
		turtle.relocate(getMaxWidth() / 2 - turtle.getBoundsInLocal().getWidth() / 2 + t.getCurrentLocation().getX(), 
				getMaxHeight() /2  - turtle.getBoundsInLocal().getHeight() / 2 - t.getCurrentLocation().getY());
	}

	@Override
	public void addLine(LineModel l) {
		Line lineToAdd = new Line(getMaxWidth() / 2 + l.getStart().getX(), 
				getMaxHeight() / 2 - l.getStart().getY(), 
				getMaxWidth() / 2 + l.getEnd().getX(), 
				getMaxHeight() /2 - l.getEnd().getY());
		lineToAdd.setFill(l.getColor());
		myLines.put(l, lineToAdd);
		getChildren().add(lineToAdd);
	}

	@Override
	public void removeLine(LineModel l) {	
		getChildren().remove(myLines.get(l));
	}


	@Override
	public void colorChange(int index, Color newColor) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void backgroundChange(Color newColor) {
		this.setStyle("-fx-background-color: #" + newColor.toString().substring(2, 8) + ";");
	}
}
