package com.braintree.creditcard.report;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.braintree.creditcard.domain.CreditCard;
import com.braintree.creditcard.report.SummaryGenerator;

public class SummaryGeneratorTest {

	SummaryGenerator gen;
	Map<String,CreditCard> map;
	@Before
	public void setUp() throws Exception {
		map= new TreeMap<String,CreditCard>();
		gen = new SummaryGenerator();
	}

	@After
	public void tearDown() throws Exception {
		map = null;
		gen = null;
	}

	@Test
	public void testGenerateSummarybyName() {
		map.put("Orange", createNewCreditCard("Orange"));
		map.put("Banana", createNewCreditCard("Banana"));
		map.put("Broccoli", createNewCreditCard("Broccoli"));
		map.put("Potato", createNewCreditCard("Potato"));
		map.put("Apple", createNewCreditCard("Apple"));
		System.out.println(gen.generateSummarybyName(map));
		assertTrue(true);

	}
	
	private CreditCard createNewCreditCard(String name){
		CreditCard cc = new CreditCard();
		cc.setName(name);
		cc.setBalance(100);
		cc.setLuhnValid(true);
		return cc;
	}

}
