package Model;

import javafx.scene.paint.Paint;

/**
 * A class to represent line segments with a width and a color. Created by turtles and used by the Enclosure and the View.
 * @author Weston
 *
 */
public class LineModel {
	
	private Point myStart;
	private Point myEnd;
	private Paint myLineColor;
	private int myWidth;
	
	
	public LineModel(Point start, Point end, Paint color, int width){
		myStart = start;
		myEnd = end;
		myLineColor = color;
		myWidth = width;
	}
	
	/**
	 * Gets the line's start point
	 * @return (Point) line's start point
	 */
	public Point getStart(){
		return myStart;
	}
	/**
	 * Gets the line's end point
	 * @return (Point) line's end point
	 */
	public Point getEnd(){
		return myEnd;
	}
	/**
	 * Gets the line's color
	 * @return (Color) line's color
	 */
	public Paint getColor(){
		return myLineColor;
	}
	/**
	 * Gets the line's width
	 * @return (Width) line's width
	 */
	public int getWidth(){
		return  myWidth;
	}
}
