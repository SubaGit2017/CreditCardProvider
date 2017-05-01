package com.braintree.creditcard.validator;


import static org.junit.Assert.*;

import javax.xml.bind.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.braintree.creditcard.validator.CardValidator;


public class CardValidatorTest {
	CardValidator validator;

	@Before
	public void setUp() throws Exception {
	 validator = new CardValidator();
	}

	@After
	public void tearDown() throws Exception {
		validator = null;
	}

	@Test
	public void testValidate() {
		String validCard = new String("5454545454545454");
		String inValidCard = new String("1234567890123456");
		assertTrue(validator.validate(validCard));
		assertFalse(validator.validate(inValidCard));
	}
	
	

	@Test
	public void testCheckCardLength() {
		String validCard = new String("5454545454545454");
		String inValidCard = new String("123456789101112131415");
		assertTrue(validator.checkCardLength(validCard));
		assertFalse(validator.checkCardLength(inValidCard));
	}

}
