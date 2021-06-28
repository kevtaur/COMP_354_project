package eternity.math;

import eternity.exception.OutOfRangeException;
import java.util.*;

public class Functions {

    /***
     * @param x: Domain [-1, 1]
     * @return: Range [0, PI], in Rad
     */
    public static double arccosine(double x) throws OutOfRangeException {
        if (x < -1 || x > 1)
            throw new OutOfRangeException();

        if (x == 0)
            return Math.PI / 2;
        else if (x == 1)
            return 0;
        else if (x == -1)
            return Math.PI;

        // Taylor Series (14th approximation)
        double result = x;

        for (int n = 1; n < 14; n++) {
            result += (semifactorial(2 * n - 1) / semifactorial(2 * n)) * (Math.pow(x, 2 * n + 1) / (2 * n + 1));
        }

        return Math.PI / 2 - result;
    }

    public static double semifactorial(int x) {
        int result = 1;

        while (x > 0) {
            result *= x;
            x -= 2;
        }

        return result;
    }

    /**
     * Taylor Series (20th approximation)
     * e^x
     *
     * @param x real number
     * @return real number
     */
    private static double natural_exponential(double x) {
        double result = 1;
        int loop = 20;
        int start = 1;

        while (start <= loop) {
            result += Math.pow(x, start) / factorial(start);
            start++;
        }
        return result;
    }

    private static double factorial(int n) {
        if (n == 0)
            return 1;
        else {
            return n * factorial(n - 1);
        }
    }

    private static double ln_derivative(double x, int derivDegree) {
        if (derivDegree == 1)
            return Math.pow(x, -1);
        else {
            double prefix = 1;
            for (int i = 1; i < derivDegree; i++) {
                prefix *= (-1 * i);
            }
            return prefix * Math.pow(x, derivDegree * -1);
        }
    }

    /**
     * ab^x
     * @param a real number
     * @param b real number
     * @param x real number
     * @return real number
     */
    public static double exponential(double a, double b, double x) {
        return a * natural_exponential(x * Math.log(b));
    }
    
    public static double std_dev(ArrayList<Double> a) {
    	double sd = 0;
    	double sum = 0;
    	for (double i : a)
    		sum += i;
    	double mean = sum / a.size();
    	for (double num : a)
    		sd += Math.pow(num - mean, 2);
    	sd = Math.sqrt(sd/a.size());
    	return sd;
    }
    
    /**
     * Mean Absolute Deviation (MAD)
     * @param d an array of real numbers 
     * @return a real number
     */
    public static double meanAbsoluteDeviation(double[] dataSet) {
    	double avg = 0;
        for (double x : dataSet) {
        	avg += x;
        }
        avg /= dataSet.length;		// Calculate mean of dataSet
        
        double madSum = 0;
		for (double x : dataSet) {
			if (x < avg)			// absolute deviation from data point to calculated mean
				madSum += avg - x;
			else
				madSum += x - avg;
        }
		
		return madSum / dataSet.length;	// return mean absolute deviation
    }
}
