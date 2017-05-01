package com.braintree.creditcard.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.braintree.creditcard.domain.CreditCard;

public class CardProcessorTest {

	CardProcessor processor;
	String[] validCard, inValidCard, chargeCard, creditCard, chargeLimit, chargeInvalidCard, creditInvalidCard;

	@Before
	public void setUp() throws Exception {
		processor = new CardProcessor();
		validCard = new String[] { "Add", "Tom", "4111111111111111", "$1000" };
		inValidCard = new String[] { "Add", "Quincy", "1234567890123456", "$1000" };
		chargeCard = new String[] { "Charge", "Tom", "$500" };
		creditCard = new String[] { "Credit", "Tom", "$700" };
		chargeLimit = new String[] { "Charge", "Tom", "$5000" };
		chargeInvalidCard = new String[] { "Charge", "Quincy", "$500" };
		creditInvalidCard = new String[] { "Credit", "Quincy", "$500" };
	}

	@After
	public void tearDown() throws Exception {
		processor = null;
		validCard = inValidCard = chargeCard = creditCard = chargeLimit = chargeInvalidCard = creditInvalidCard = null;

	}

	@Test
	public void testAddCreditCard() {
		processor = new CardProcessor();

		processor.addCreditCard(validCard);

		Map<String, CreditCard> cardMap = processor.getCards();
		assertNotNull(cardMap);
		CreditCard cc = cardMap.get(validCard[1]);
		assertNotNull(cc);
		assertEquals(validCard[2], cc.getCardNum());
		assertEquals(validCard[3], "$"+cc.getLimit());
		assertEquals(true, cc.isLuhnValid());

		processor.addCreditCard(inValidCard);
		assertNotNull(cardMap);
		cc = cardMap.get(inValidCard[1]);
		assertNotNull(cc);
		assertNull(cc.getCardNum());
		assertEquals(0, cc.getLimit());
		assertEquals(false, cc.isLuhnValid());
	}

	@Test
	public void testChargeAmount() {
		processor = new CardProcessor();

		// charge checkprocessor
		processor.addCreditCard(validCard);
		processor.chargeAmount(chargeCard);
		Map<String, CreditCard> cardMap = processor.getCards();
		assertNotNull(cardMap);
		CreditCard cc = cardMap.get(chargeCard[1]);
		assertNotNull(cc);
		assertEquals(500, cc.getBalance());

		// charge more than available limit check
		processor.chargeAmount(chargeLimit);
		assertEquals(500, cc.getBalance());

		// No transaction allowed on luhnfailed card
		processor.addCreditCard(inValidCard);
		processor.chargeAmount(chargeInvalidCard);
		cc = cardMap.get(chargeInvalidCard[1]);
		assertNotNull(cc);
		assertEquals(0, cc.getBalance());
	}

	@Test
	public void testCreditAmount() {
		processor = new CardProcessor();

		// credit check
		processor.addCreditCard(validCard);
		processor.creditAmount(creditCard);
		Map<String, CreditCard> cardMap = processor.getCards();
		assertNotNull(cardMap);
		CreditCard cc = cardMap.get(creditCard[1]);
		assertNotNull(cc);
		assertEquals(-700, cc.getBalance());

		// No transaction allowed on luhnfailed card
		processor.addCreditCard(inValidCard);
		processor.creditAmount(creditInvalidCard);
		cc = cardMap.get(creditInvalidCard[1]);
		assertNotNull(cc);
		assertEquals(0, cc.getBalance());
	}

	@Test
	public void testGetCards() {
		processor = new CardProcessor();
		processor.addCreditCard(validCard);
		processor.addCreditCard(inValidCard);
		
		assertNotNull(processor.getCards());
		assertEquals(2,processor.getCards().size());

	}

}
