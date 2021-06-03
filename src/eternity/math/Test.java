package eternity.math;

import eternity.exception.OutOfRangeException;

public class Test {
	public static void main(String[] args) {
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
	}
}
