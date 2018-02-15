package pl.adam.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

	protected static final int PLACES = 2;

	public Calculator() {

	}

	public static double add(double num1, double num2) {

		if ((num1 + num2) > Double.MAX_VALUE) {
			throw new IllegalArgumentException("The values are too big.");
		} else {
			double sum = num1 + num2;
			return round(sum, PLACES);
		}
	}

	public static double subtract(double num1, double num2) {
		double subtract = num1 - num2;
		return round(subtract, PLACES);

	}

	public static double multiply(double num1, double num2) {
		double multiply = num1 * num2;
		return round(multiply, PLACES);
	}

	public static double divide(double num1, double num2) {
		if (num2 == 0) {
			throw new ArithmeticException("You can not divide by 0!");
		}

		double division = num1 / num2;
		return round(division, PLACES);

	}

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException("Rounding have to be positive value");
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_DOWN);
		return bd.doubleValue();
	}

}
