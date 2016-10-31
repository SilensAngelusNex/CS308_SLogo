package Model;

import javafx.scene.paint.Paint;

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
	
	public Point getStart(){
		return myStart;
	}
	public Point getEnd(){
		return myEnd;
	}
	public Paint getColor(){
		return myLineColor;
	}
	public int getWidth(){
		return  myWidth;
	}
}
