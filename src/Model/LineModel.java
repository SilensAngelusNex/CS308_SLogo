package Model;

import java.awt.Color;

public class LineModel {
	
	private Point myStart;
	private Point myEnd;
	private Color myLineColor;
	private int myWidth;
	
	
	public LineModel(Point start, Point end, Color color, int width){
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
	public Color getColor(){
		return myLineColor;
	}
	public int getWidth(){
		return  myWidth;
	}
}
