package Model;

import java.awt.Color;

public class LineModel {
	
	private Point myStart;
	private Point myEnd;
	private Color myLineColor;
	
	
	public LineModel(Point start, Point end, Color color){
		myStart = start;
		myEnd = end;
		myLineColor = color;
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
}
