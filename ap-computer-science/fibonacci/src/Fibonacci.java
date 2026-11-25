import java.util.ArrayList;

import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.math.*;

public class Fibonacci {
	
	private static ArrayList<BigInteger> knownCases = new ArrayList<BigInteger>();
	
	public static void main(String[] args) {
		knownCases.add(new BigInteger("0"));
		knownCases.add(new BigInteger("1"));

		//recursiveFibTest();
		//nonRecusiveFibTest();
		//withArrayListFibTest();
		
		System.out.println(recursiveFibonacci(new BigInteger("10")));
		System.out.println(fibonacci(new BigInteger("10")));
		System.out.println(fibonacciWithArrayList(new BigInteger("10")));
	}
	
	public static BigInteger recursiveFibonacci(BigInteger n) {
		if((n.compareTo(new BigInteger("1")) <= 0)) {
			return n;
		}
		return recursiveFibonacci(n.subtract(new BigInteger("1"))).add(recursiveFibonacci(n.subtract(new BigInteger("2"))));
	}
	
	public static BigInteger fibonacci(BigInteger n) {
		if(n.compareTo(new BigInteger("0")) == 0) {return new BigInteger("0");}
		
		BigInteger num1 = new BigInteger("0");
		BigInteger num2 = new BigInteger("1");
		int lim = n.intValue();
		for(int i = 2; i<lim; i++) {
			BigInteger temp = num1;
			num1 = num2;
			num2 = num2.add(temp);
		}
		
		return num1.add(num2);
	}
	
	public static BigInteger fibonacciWithArrayList(BigInteger n) {
		if(n.intValue() < knownCases.size()) {
			return new BigInteger(knownCases.get(n.intValue())+"");
		}
		knownCases.add(fibonacciWithArrayList(n.subtract(new BigInteger("1"))).add(fibonacciWithArrayList(n.subtract(new BigInteger("2")))));
		
		return knownCases.get(n.intValue());
	}
	
	public static void recursiveFibTest() {
		long startTime1 = System.nanoTime();
		recursiveFibonacci(new BigInteger("10"));
	    long endTime1 = System.nanoTime();
	    long duration1 = (endTime1 - startTime1);
	    
	    long startTime2 = System.nanoTime();
		recursiveFibonacci(new BigInteger("25"));
	    long endTime2 = System.nanoTime();
	    long duration2 = (endTime2 - startTime2);
	    
	    long startTime3 = System.nanoTime();
		recursiveFibonacci(new BigInteger("50"));
	    long endTime3 = System.nanoTime();
	    long duration3 = (endTime3 - startTime3);
	    
	    System.out.println(duration1 + " " + duration2 + " " + duration3);
	}
	
	public static void nonRecusiveFibTest() {
		long startTime1 = System.nanoTime();
		fibonacci(new BigInteger("10"));
	    long endTime1 = System.nanoTime();
	    long duration1 = (endTime1 - startTime1);
	    
	    long startTime2 = System.nanoTime();
	    fibonacci(new BigInteger("25"));
	    long endTime2 = System.nanoTime();
	    long duration2 = (endTime2 - startTime2);
	    
	    long startTime3 = System.nanoTime();
	    fibonacci(new BigInteger("50"));
	    long endTime3 = System.nanoTime();
	    long duration3 = (endTime3 - startTime3);
	    
	    System.out.println(duration1 + " " + duration2 + " " + duration3);
	}
	
	public static void withArrayListFibTest() {
		long startTime1 = System.nanoTime();
		fibonacciWithArrayList(new BigInteger("10"));
	    long endTime1 = System.nanoTime();
	    long duration1 = (endTime1 - startTime1);
	    
	    long startTime2 = System.nanoTime();
	    fibonacciWithArrayList(new BigInteger("25"));
	    long endTime2 = System.nanoTime();
	    long duration2 = (endTime2 - startTime2);
	    
	    long startTime3 = System.nanoTime();
	    fibonacciWithArrayList(new BigInteger("50"));
	    long endTime3 = System.nanoTime();
	    long duration3 = (endTime3 - startTime3);
	    
	    System.out.println(duration1 + " " + duration2 + " " + duration3);
	}
}
