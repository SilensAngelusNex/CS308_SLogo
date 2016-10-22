package Model;

public class Point {
	double myX;
	double myY;
	
	public Point(Number x, Number y){
		myX = x.doubleValue();
		myY = y.doubleValue();
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public void setLocation(Number x, Number y){
		myX = x.doubleValue();
		myY = y.doubleValue();
	}
	
	public double euclideanDistance(Point p){
		return Math.sqrt(
				Math.pow(myX - p.myX, 2) +
				Math.pow(myY - p.myY, 2)
				);
	}
	
	public double headingTo(Point p){
		return Math.atan((p.myY - myY) / (p.myX - myX));
	}
}
