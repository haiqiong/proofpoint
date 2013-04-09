package proofpoint;

/**
 * http://en.wikipedia.org/wiki/Factorial
 * calculate the trailing zero of n!.
 * @author haiqiongyao
 * 4/8/2013
 */

public class TrailingZero {
	public int getFactorialTrailingZero(int n) {
		//the number of number ending at 5 and less than n.
		int fiveCount = n / 5;
		//the number of number ending at 0 and less than n.
		int zeroCount = n / 10;
		fiveCount -= zeroCount;
		//the number of 2.
		int twoCount = n / 2;
		
		return zeroCount + Math.min(fiveCount, twoCount);
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 5, 9, 10, 12, 15, 19, 20, 25, 42, 50, 70, 100};
		TrailingZero trailingZero = new TrailingZero();
		for (int n : arr) {
			System.out.print(trailingZero.getFactorialTrailingZero(n) + ", ");
		}
	}
}
