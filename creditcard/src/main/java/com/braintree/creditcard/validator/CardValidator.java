package com.braintree.creditcard.validator;

import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

/**
 * Validator that validates the credit card information LuhnCheck validation is
 * imported from commons validator jar
 * 
 * @author Subashini
 *
 */
public class CardValidator {

	public boolean validate(String code) {
		LuhnCheckDigit luhn = new LuhnCheckDigit();
		return luhn.isValid(code);
	}

	public boolean checkCardLength(String cardNum) {
		boolean value = false;
		if (cardNum != null) {
			if (cardNum.length() <= 19) {
				value = true;
			}
		}
		return value;

	}

}
