package pl.adam.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void addSmallValues() {
		double small = 2 + 2;
		assertEquals(small, Calculator.add(2, 2), 0.01);
	}

	@Test
	public void addNegativeValues() {
		double addNegativeValue = 2 + (-5);
		assertEquals(addNegativeValue, Calculator.add(2, -5), 0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addTooBigValuesShouldReturnIllegalArgEx() {
		Calculator.add(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	@Test
	public void roundForSmallValues() {
		double value = 2.01234567;
		assertEquals(2.01, Calculator.round(value, Calculator.PLACES), 0.001);
	}

	@Test
	public void roundForSmallValuesHalfDown() {
		double value = 2.045;
		assertEquals(2.04, Calculator.round(value, Calculator.PLACES), 0.001);
	}

	@Test
	public void roundForSmallValuesHalfUp() {
		double value = 2.046;
		assertEquals(2.05, Calculator.round(value, Calculator.PLACES), 0.001);
	}
}