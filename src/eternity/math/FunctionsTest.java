package eternity.math;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import eternity.exception.OutOfRangeException;

class FunctionsTest {
	
	@Test
	@DisplayName("Arccosine")
	void testArccosine() {
		
		class Case {
			String name;
			double input;
			double expectedResult;
			Exception exception;
			
			public Case(String name, double input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}
		
		Case cases[] = new Case[] {
				new Case("Input = 0", 0, Math.PI/2, null),
				new Case("Input = 1", 1, 0, null),
				new Case("Input = -1", -1, Math.PI, null),
				new Case("Input = 10", 10, 0, new OutOfRangeException()),
				new Case("Input = -25", 10, 0, new OutOfRangeException()),
				new Case("Input = 0.5", 0.5, 1.04719, null),
				new Case("Input = -0.5", -0.5, 2.09439, null),
		};
		
		System.out.println("\nTest: Arccosine");
		for (Case scenario : cases) {
			System.out.println(scenario.name);
			
			if (scenario.exception == null) {
				try {
					assertEquals(new BigDecimal(scenario.expectedResult).setScale(5, RoundingMode.DOWN).doubleValue(), 
							new BigDecimal(Functions.arccosine(scenario.input)).setScale(5, RoundingMode.DOWN).doubleValue());
				} catch (Exception e) {
					fail("Unepexted exception thrown");
				}
			}
			
			else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.arccosine(scenario.input));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}
	
	
	@Test
	void testSemifactorial() {
		class Case {
			String name;
			int input;
			double expectedResult;
			Exception exception;
			
			public Case(String name, int input, double expectedResult, Exception exception) {
				this.name = name;
				this.input = input;
				this.expectedResult = expectedResult;
				this.exception = exception;
			}
		}
		
		Case cases[] = new Case[] {
				new Case("Input = -4", -4, 0, new OutOfRangeException()),
				new Case("Input = 0", 0, 1, null),
				new Case("Input = 5", 5, 15, null),
				new Case("Input = 10", 10, 3840, null),
				new Case("Input = 15", 15, 2027025, null),
		};
		
		System.out.println("\nTest: Double Factorial");
		for (Case scenario : cases) {
			System.out.println(scenario.name);
			
			if (scenario.exception == null) {
				try {
					assertEquals(scenario.expectedResult, (double) Functions.semifactorial(scenario.input));
				} catch (Exception e) {
					fail("Unepexted exception thrown");
				}
			}
			
			else {
				Throwable exception = assertThrows(scenario.exception.getClass(), () -> Functions.arccosine(scenario.input));
				assertEquals(scenario.exception.getMessage(), exception.getMessage());
			}
		}
	}
	
	
	/**
	@Test
	void testExponential() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	@Test
	void testStd_dev() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	@Test
	void testMeanAbsoluteDeviation() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	@Test
	void testLogDouble() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	@Test
	void testLogDoubleDouble() {
		fail("Not yet implemented");
	}
	*/
	
	/**
	@Test
	void testSinh() {
		fail("Not yet implemented");
	}
	 */
}
