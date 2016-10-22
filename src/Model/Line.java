package Model;

import java.awt.Color;

public class Line {
	
	Point myStart;
	Point myEnd;
	Color myLineColor;
	
	
	public Line(Point start, Point end, Color color){
		myStart = start;
		myEnd = end;
		myLineColor = color;
	}
}
