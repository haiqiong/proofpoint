package proofpoint;

/**
 * magic number: It is a six digit number. 
 * The sum of the first three digits and the second three digits squared 
 * is equal to the original six digit number.
 * Range: 100000 <= 999999
 * Example: 998001 == (998+001)^2
 * @author haiqiongyao
 * 4/8/2013
 */

public class MagicNumber {
	public void getMagicNumber(int lowerRange, int upperRange) {
		int start = (int) Math.ceil(Math.sqrt(lowerRange));
		int end = (int) Math.ceil(Math.sqrt(upperRange));
		int magicNum = 0;
		int firstPart = 0, secondPart = 0;
		
		for (int i = start; i < end; i++) {
			magicNum = i * i;
			firstPart = magicNum / 1000;
			secondPart = magicNum - firstPart * 1000;
			
			if (Math.pow(firstPart + secondPart, 2) == magicNum ) {
				System.out.println(magicNum);
			}
		}
	}
	
	public static void main(String[] args) {
		int lowerRange = 100000, upperRange = 999999;
		new MagicNumber().getMagicNumber(lowerRange, upperRange);
	}
}
