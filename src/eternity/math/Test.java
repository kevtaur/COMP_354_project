	package eternity.math;

import eternity.exception.OutOfRangeException;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		System.out.println("Welcome to Eternity!");
		System.out.println("Testing arccos()");
		try {
			System.out.println(Functions.arccosine(1));
			System.out.println(Functions.arccosine(0.5));
			System.out.println(Functions.arccosine(0));
			System.out.println(Functions.arccosine(-0.5));
			System.out.println(Functions.arccosine(-1));
		}
		catch (OutOfRangeException e) {
			System.out.println("MATH ERROR");
		}
		
		System.out.println("Test standard deviation");
		try {
			ArrayList<Double> test = new ArrayList<>();
			test.add(3.0);
			test.add(4.0);
			test.add(8.6);
			test.add(9.4);
			test.add(49.3);
			System.out.println(Functions.std_dev(test));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nTesting meanAbsoluteDeviation()");
		try {
			double arr[] = {1.0 ,2.0, 3.0, 4.0, 5.0, 6.0};
			System.out.println(Functions.meanAbsoluteDeviation(arr));
			assert Functions.meanAbsoluteDeviation(arr) == 1.5;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Program End.");
	}
}
