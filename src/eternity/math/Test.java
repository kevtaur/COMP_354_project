	package eternity.math;

import eternity.exception.OutOfRangeException;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		try {
			System.out.println(Functions.arccosine(1));
			System.out.println(Functions.arccosine(0.5));
			System.out.println(Functions.arccosine(0));
			System.out.println(Functions.arccosine(-0.5));
			System.out.println(Functions.arccosine(-1));
			ArrayList<Double> test = new ArrayList<>();
			test.add(3.0);
			test.add(4.0);
			test.add(8.6);
			test.add(9.4);
			test.add(49.3);
			System.out.println("Test standard deviation");
			System.out.println(Functions.std_dev(test));
			
		}
		catch (OutOfRangeException e) {
			System.out.println("MATH ERROR");
		}
	}
}
