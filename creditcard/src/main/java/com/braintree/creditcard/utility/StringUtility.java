package com.braintree.creditcard.utility;

/**
 * Utility to remove and add Dollar sign for amounts.
 * 
 * @author Subashini
 *
 */
public class StringUtility {

	// $ check

	public static int removeDollarSign(String amount) {
		return Integer.parseInt(amount.substring((amount.indexOf('$') + 1)));
	}

	public static String addDollarSign(int amount) {
		return new String("$" + amount);
	}
}
