package Model;

import java.util.Random;

/**
 * @deprecated
 * @author Weston
 *
 */
public class Calculator {
	private Random myRandom = new Random();

	public double sum(double a, double b) {
		return a + b;
	}

	public double difference(double a, double b) {
		return a - b;
	}

	public double product(double a, double b) {
		return a * b;
	}

	public double quotient(double a, double b) {
		return a / b;
	}

	public double remainder(double a, double b) {
		return a % b;
	}

	public double minus(double a) {
		return -a;
	}

	public double random(double max) {
		return myRandom.nextDouble() * max;
	}

	public double sin(double degrees) {
		return Math.sin(Math.toRadians(degrees));
	}

	public double cos(double degrees) {
		return Math.cos(Math.toRadians(degrees));
	}

	public double tan(double degrees) {
		return Math.tan(Math.toRadians(degrees));
	}

	public double atan(double degrees) {
		return Math.atan(Math.toRadians(degrees));
	}

	public double log(double a) {
		return Math.log(a);
	}

	public double pow(double a, double b) {
		return Math.pow(a, b);
	}

	public double pi() {
		return Math.PI;
	}

	public double less(double a, double b) {
		return (a < b) ? 1 : 0;
	}

	public double greater(double a, double b) {
		return (a > b) ? 1 : 0;
	}

	public double equal(double a, double b) {
		return (a == b) ? 1 : 0;
	}

	public double notEqual(double a, double b) {
		return (a != b) ? 1 : 0;
	}

	public double and(double a, double b) {
		return (a != 0 && b != 0) ? 1 : 0;
	}

	public double or(double a, double b) {
		return (a != 0 || b != 0) ? 1 : 0;
	}

	public double not(double a) {
		return (a == 0) ? 1 : 0;
	}

}
