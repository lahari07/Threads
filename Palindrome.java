/*
 * Palindrome.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * A program to find out if a number is Delayed Palindrome or Lychrel Number
 * 
 * Delayed Palindrome: A number that results in a Palindrome after iterative
 * process of adding the number after reversing the digits. Lychrel Number: A
 * number that doesnot result in a palindrome even after iterative process of
 * adding a number with it's reverse.
 * 
 * @author Lahari Chepuri
 * @author Smita Subhadarshinee Mishra
 */

public class Palindrome extends Thread{

	final static int START = 78 ;
	final static int MAXIMUM = 88 ;
	final static int MAXIMUM_DELAYED = 10 ;
	final int numberToCheck;
	static String[] arrayToPrint = new String[(MAXIMUM - START)+1];
	/**
	 * The main program.
	 *
	 * @param args command line arguments (ignored)
	 */

	public Palindrome (int numberToCheck ) {
		this.numberToCheck = numberToCheck;
	}
	
	public static void main ( String [] args ) {
		
		managerThreads () ;

	}

	private static void managerThreads ( ) {
		Palindrome[] palindromeArr = new Palindrome [(MAXIMUM - START)+1 ];

		// Iterate over the range and check for each number if the property
		// satisfies.
		for ( int i = START ; i <= MAXIMUM ; i ++ ) {
			palindromeArr[i-START] = new Palindrome ( i );
			palindromeArr[i-START].start();
		}
		for ( int i = 0 ; i < palindromeArr.length ; i ++ ) {
			try {
				palindromeArr[i].join ();
			} catch ( Exception e ) {
				System.out.println ( "Interrupted" ) ;
			}
		}
		
		for ( int i = 0 ; i < arrayToPrint.length ; i ++ ) {
			System.out.println ( arrayToPrint[i] ) ;
		}
		
	}

	public void run ( ) {
		int sum ;
		int reverse ;
		int currentNumber = numberToCheck;
		String reverseAsString ;
		boolean isLychrelNumber = false ;
		sum = 0 ;
		reverseAsString = "" ;
		

		// Check for property only until the MAXIMUM_DELAYED number, if we
		// don't find the number satisfying by then, come out of the loop.
		for ( int delay = 0 ; delay < MAXIMUM_DELAYED ; delay ++ ) {

			// Get the reversed digits of the number
			reverseAsString = findReverse ( currentNumber ) ;
			reverse = Integer.parseInt ( reverseAsString ) ;
			sum = currentNumber + reverse ;

			// if the sum is a Palindrome, then property satisfies and we
			// print the output.
			if ( checkPalindrome ( sum ) ) {
				arrayToPrint[numberToCheck - START] = numberToCheck + ":     delayed " + ( delay + 1 ) + ":   " + currentNumber + " + "
						+ reverseAsString + " = " + sum ;
				
				isLychrelNumber = true ;
				break ;
			} else {
				// if the number is not palindrome then check if the
				// resulting sum has the property.
				currentNumber = sum ;
			}

		}

		// If it is not a Lychrel Number, print the output.
		if ( isLychrelNumber == false ) {
			arrayToPrint[numberToCheck - START] = numberToCheck + ":     delayed " + MAXIMUM_DELAYED + ":   " 
													+ "does not become palindromic within 3 iterations:  "
													+ currentNumber + " + " + reverseAsString + "=" + sum
													+ ":   " + sum + "!=" + findReverse ( sum );
		}
	}

	/**
	 * Returns the reverse of the digits of the number passed into the method
	 * parameter as a string.
	 *
	 * @param number: int The number whose digit needs to be reversed
	 * 
	 * @return numAsString: String The number with its digits reversed
	 */

	public static String findReverse ( int number ) {

		// Converting the number to string
		String numAsString = String.valueOf ( number ) ;

		// Using string builder class to find out the reversed digits.
		StringBuilder sb = new StringBuilder ( numAsString ) ;
		numAsString = sb.reverse ().toString () ;
		return numAsString ;

	}

	/**
	 * Returns true if the number is a Palindrome, else false.
	 *
	 * @param number: int The number that needs to be checked for Palindrome
	 *                property.
	 * 
	 * @return Boolean True if the number is palindrome, else False.
	 */

	public static boolean checkPalindrome ( int number ) {
		int reverse = Integer.parseInt ( findReverse ( number ) ) ;
		String numAsString = String.valueOf ( number ) ;
		String revAsString = String.valueOf ( reverse ) ;

		// Compare if the number reads same from both directions, forward and
		// reverse
		if ( numAsString.equals ( revAsString ) ) {
			return true ;
		} else {
			return false ;
		}

	}

}
