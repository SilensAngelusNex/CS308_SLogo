package Model;

import java.awt.Color;
import java.awt.Point;

public class Line {
	
	Point myStart;
	Point myEnd;
	Color myLineColor;
	
	
	public Line(Point startPoint, Point endPoint, Color color){
		myStart = startPoint;
		myEnd = endPoint;
		myLineColor = color;
	}
}
