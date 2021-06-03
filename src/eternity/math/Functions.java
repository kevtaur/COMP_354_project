package eternity.math;

import eternity.exception.OutOfRangeException;

public class Functions {
	
	/***
	 * @param x: Domain [-1, 1]
	 * @return: Range [0, PI], in Rad
	 */
	public static double arccosine(double x) throws OutOfRangeException{
		if (x < -1 || x > 1)
			throw new OutOfRangeException();
		
		if (x == 0)
			return Math.PI/2;
		else if (x == 1)
			return 0;
		else if (x == -1)
			return Math.PI;
		
		// Taylor Series (14th approximation)
		double result = x;
		
		for (int n = 1; n < 14; n++) {
			result += (semifactorial(2*n - 1) / semifactorial(2*n)) * (Math.pow(x, 2*n + 1) / (2*n + 1)); 
		}
		
		return Math.PI/2 - result;
	}
	
	public static double semifactorial(int x) {
		int result = 1;
		
		while (x > 0) {
			result *= x;
			x -= 2;
		}
		
		return result;
	}
}
