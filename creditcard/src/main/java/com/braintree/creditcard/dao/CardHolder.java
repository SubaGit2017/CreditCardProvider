package com.braintree.creditcard.dao;

import java.util.Map;
import java.util.TreeMap;

import com.braintree.creditcard.domain.CreditCard;

/**
 * This classs is used to persist the objects Currently map storage is
 * implemeted. It can be extended to database or file or cache.
 * 
 * @author Subashini
 *
 */
public class CardHolder {

	Map<String, CreditCard> cardMap = new TreeMap<String, CreditCard>();

	public void addCreditCard(CreditCard cc) {
		cardMap.put(cc.getName(), cc);

	}

	public Map<String, CreditCard> getCards() {
		return this.cardMap;
	}
}
