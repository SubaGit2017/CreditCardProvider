package com.braintree.creditcard.dao;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.braintree.creditcard.domain.CreditCard;

public class CardHolderTest {

	CardHolder holder;
	CreditCard validCard, inValidCard;


	@Before
	public void setUp() throws Exception {
		holder = new CardHolder();
		validCard = new CreditCard();
		validCard.setName("Tom");
		validCard.setLuhnValid(true);
		validCard.setCardNum("4111111111111111");
		validCard.setLimit(1000);
		
		inValidCard = new CreditCard();
		inValidCard.setName("Quincy");
		inValidCard.setLuhnValid(false);
		inValidCard.setCardNum("1234567890123456");
		inValidCard.setLimit(1000);
				
	}

	@After
	public void tearDown() throws Exception {
		holder = null;
		validCard = inValidCard=null;
	}

	@Test
	public void testAddCreditCard() {
		holder = new CardHolder();
		holder.addCreditCard(validCard);
		
		assertNotNull(holder.getCards());
		assertEquals(1,holder.getCards().size());

	}

	@Test
	public void testGetCards() {
		holder = new CardHolder();
		holder.addCreditCard(validCard);
		holder.addCreditCard(inValidCard);
		
		assertNotNull(holder.getCards());
		assertEquals(2,holder.getCards().size());

	}

}
