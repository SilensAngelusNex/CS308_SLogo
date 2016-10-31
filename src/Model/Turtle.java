package Model;

import javafx.scene.paint.Color;

import View.TurtleView;

public class Turtle implements TurtleModel, TurtleView{
	private Point myPosition;
	private Point myLastPosition;
	private String myTurtleImage;
	private double myHeading;
	private double myLastHeading;
	
	private Color myPenColor;
	private int myPenSize;
	private boolean myPenDown;
	
	private boolean myVisibility;
	private int myShapeIndex;
	
	public Turtle(){
		myPosition = new Point(0, 0);
		myHeading = Math.toRadians(90);
		myLastPosition = myPosition;
		myLastHeading = myHeading;
		myTurtleImage = "turtle.png";
		myPenDown = true;
		myPenColor = Color.color(255, 255, 255);
		myPenSize = 2;
		myVisibility = true;
		myShapeIndex = 1;
	}
	
	@Override
	public double getHeading(){
		return myHeading;
	}
	
	@Override
	public double setHeading(double radians) {
		myLastHeading = myHeading;
		double result = radians - myHeading ;
		
		radians = radians % (2 * Math.PI);

		if (radians < 0)
			radians += 2 * Math.PI;
		
		myHeading = radians;
		
		return result;
	}
	
	@Override
	public double getX(){
		return myPosition.getX();
	}
	@Override
	public double getY(){
		return myPosition.getY();
	}

	@Override
	public boolean isDrawing() {
		return myPenDown;
	}

	@Override
	public void setLocation(double newX, double newY) {
		myLastPosition = myPosition;
		myPosition = new Point(newX, newY);
	}

	@Override
	public Color getPenColor() {
		return myPenColor;
	}

	public double setPenColor(Color c) {
		myPenColor = c;
		return 1;
	}
	

	@Override
	public Point getCurrentLocation() {
		return myPosition;
	}


	@Override
	public double getCurrentHeading() {
		return 90 - Math.toDegrees(myHeading);
	}


	@Override
	public Point getPreviousLocation() {
		return myLastPosition;
	}


	@Override
	public double getPreviousHeading() {
		return 90 - Math.toDegrees(myLastHeading);
	}


	@Override
	public String getImagePath() {
		return myTurtleImage;
	}

	@Override
	public TurtleView toTurtleView(){
		return this;
	}
	
	@Override
	public void setVisibility(boolean b){
		myVisibility = b;
	}

	@Override
	public void setPen(boolean b) {
		myPenDown = b;
	}

	@Override
	public boolean getPenDown() {
		return myPenDown;
	}

	@Override
	public boolean getVisibility() {
		return myVisibility;
	}

	@Override
	public void setTurtleImage(String image) {
		myTurtleImage = image;
	}

	@Override
	public double setPenSize(double size) {
		myPenSize = (int) size;
		return size;
	}

	@Override
	public int getPenSize() {
		return myPenSize;
	}

	@Override
	public int getShape() {
		return myShapeIndex;
	}

	@Override
	public void setShape(int index) {
		myShapeIndex = index;
	}
}
