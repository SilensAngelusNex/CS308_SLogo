package Model;

import javafx.scene.paint.Color;
import parser.InvalidCommandException;
import Model.Commands.Command;
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
	private TurtleContainer myContainer;
	private ColorPallet myColors;
	
	public Turtle(ColorPallet colors, TurtleContainer container){
		//TODO: put in resource file.
		myTurtleImage = "turtle.png";
		
		myContainer = container;
		myColors = colors;
		
		myPosition = new Point(0, 0);
		myHeading = Math.toRadians(90);
		myLastPosition = myPosition;
		myLastHeading = myHeading;
		myPenDown = true;
		myPenColor = Color.color(0, 0, 0);
		myPenSize = 2;
		myVisibility = true;
		myShapeIndex = 1;
		
		notifyAdd();
	}
	
	@Override
	public double getHeading(){
		return Math.toDegrees(myHeading);
	}
	
	@Override
	public double setHeading(double radians) {
		myLastHeading = myHeading;
		double result = radians - myHeading ;
		
		radians = radians % (2 * Math.PI);

		if (radians < 0)
			radians += 2 * Math.PI;
		
		myHeading = radians;
		
		notifyMove();
		return Math.toDegrees(result);
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
	public double getPenColor() {
		return myColors.getIndex(myPenColor);
	}

	public double setPenColor(int index) {
		myPenColor = myColors.getColor(index);
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
	public double setVisibility(boolean b){
		myVisibility = b;
		if (b){
			notifyAdd();
			return 1;
		} else {
			notifyRemove();
			return 0;
		}
	}

	@Override
	public double setPen(boolean b) {
		myPenDown = b;
		return b ? 1 : 0;
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
	public double setShape(int index) {
		myShapeIndex = index;
		return index;
	}

	@Override
	public double forward(Command distance) throws InvalidCommandException {
		return move(distance.execute(this), myHeading);
	}

	@Override
	public double back(Command distance) throws InvalidCommandException {
		return move(distance.execute(this), Math.PI + myHeading);
	}

	@Override
	public double right(Command degrees) throws InvalidCommandException {
		return -setHeading(myHeading - Math.toRadians(degrees.execute(this)));
	}

	@Override
	public double left(Command degrees) throws InvalidCommandException {
		return setHeading(myHeading + Math.toRadians(degrees.execute(this)));
	}

	@Override
	public double setHeading(Command degrees) throws InvalidCommandException {
		return setHeading(Math.toRadians(degrees.execute(this)));
	}

	@Override
	public double towards(Command x, Command y) throws InvalidCommandException {
		Point destination = new Point(x.execute(this), y.execute(this));
		return setHeading(Math.toDegrees(myPosition.headingTo(destination)));
	}

	@Override
	public double goTo(Command x, Command y) throws InvalidCommandException {
		return goTo(x.execute(this), y.execute(this));
	}

	@Override
	public double goTo(double d, double e) {
		myLastPosition = myPosition;
		myPosition = new Point(d, e);
		notifyMove();
		return myLastPosition.euclideanDistance(myPosition);
	}
	
	private double move(double dist, double heading){
		double result = dist;
		
		while (dist > 0){
			double toEdge = distToEdge(heading);
			
			if (dist >= toEdge){
				moveNoEdgeChecking(toEdge, heading);
				wrap();
				dist -= toEdge; 
			} else {
				moveNoEdgeChecking(dist, heading);
				dist = 0;
			}
		}

		return result;
	}
	
	private void wrap(){
		double newX = getX();
		double newY = getY();
		
		if (Math.abs(newX) >= myContainer.getMaxX())
			newX *= -1;
		if (Math.abs(newY) >= myContainer.getMaxY())
			newY *= -1;
		
		goTo(newX, newY);
	}
	
	private void moveNoEdgeChecking(double dist, double heading){
		double newX = getX() + dist * Math.cos(heading);
		double newY = getY() + dist * Math.sin(heading);
		
		goTo(newX, newY);
		
		notifyMove();
		
		if (isDrawing()){
			LineModel toAdd = new LineModel(myLastPosition, myPosition, myPenColor, myPenSize);
			myContainer.addLine(toAdd);
		}
		
	}
	
	/**
	 * @param turtleNumber The index of the turtle to check the distance for.
	 * @return distance the turtle needs to travel along it's heading to hit an edge
	 */
	private double distToEdge(double heading){
		if (heading < 0)
			heading = (heading % (2 * Math.PI)) + 2 * Math.PI;
		
		double xDist = Double.POSITIVE_INFINITY;
		double yDist = Double.POSITIVE_INFINITY;
		
		//Right half of circle
		if ((heading < Math.toRadians(90) && heading >= Math.toRadians(0)) || (heading > Math.toRadians(270) && heading <= Math.toRadians(360)))
			xDist = myContainer.getMaxX() - getX();
		//Left half of circle
		if (heading > Math.toRadians(90) && heading < Math.toRadians(270))
			xDist = getX() + myContainer.getMaxX();
		
		//Upper half of circle
		if (heading > Math.toRadians(0) && heading < Math.toRadians(180))
			yDist = myContainer.getMaxY() - getY();
		//Lower half of circle
		if (heading > Math.toRadians(180) && heading < Math.toRadians(360))
			yDist = getY() + myContainer.getMaxY();
		
		xDist /= Math.cos(heading);
		yDist /= Math.sin(heading);
		
		return Math.min(Math.abs(xDist), Math.abs(yDist));
	}
	
	private void notifyAdd() {
		myContainer.turtleAdd(this);
	}
	private void notifyMove() {
		myContainer.turtleMove(this);
	}
	private void notifyRemove() {
		myContainer.turtleRemove(this);
	}

	@Override
	public double setPenSize(Command size) throws InvalidCommandException {
		return setPenSize(size.execute(this));
	}

	@Override
	public double setShape(Command shape) throws InvalidCommandException {
		return setShape((int) shape.execute(this));
	}

	@Override
	public double setPenColor(Command index) throws InvalidCommandException {
		return setPenColor((int) index.execute(this));
	}

	@Override
	public int getIndex() {
		return myContainer.getIndex(this);
	}

}

