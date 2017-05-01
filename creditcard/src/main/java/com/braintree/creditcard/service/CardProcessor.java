package com.braintree.creditcard.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import com.braintree.creditcard.dao.CardHolder;
import com.braintree.creditcard.domain.CreditCard;
import com.braintree.creditcard.utility.StringUtility;
import com.braintree.creditcard.validator.CardValidator;

/**
 * Business class that takes care of the following
 *  - Validation using validator class 
 *  - Persistence using holder class
 * 
 * @author Subashini
 *
 */
public class CardProcessor {

	CardValidator validator = new CardValidator();
	CardHolder holder = new CardHolder();

	private CreditCard isCardValid(String key) {
		if (key != null) {
			CreditCard card = (holder.getCards()).get(key);
			if (card.isLuhnValid()) {
				return card;
			}
		}
		return null;
	}

	public void addCreditCard(String[] args) {

		CreditCard cc = new CreditCard(); // define constructor to accept all
											// params
		cc.setId(UUID.randomUUID().toString());
		if (args[1] != null) {
			cc.setName(args[1]);
		}
		if (args[2] != null) {
			cc.setLuhnValid(validator.validate(args[2]));
			if (cc.isLuhnValid() && validator.checkCardLength(args[2])) {
				cc.setCardNum(args[2]);
			}
		}
		if (args[3] != null && cc.isLuhnValid()) {
			cc.setLimit(StringUtility.removeDollarSign(args[3]));
			// We can add constants file
		}
		cc.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		cc.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
		holder.addCreditCard(cc);
//		System.out.println(cc.toString());
	}

	public void chargeAmount(String[] tokens) {
		CreditCard card = isCardValid(tokens[1]);
		if (card != null && tokens[2] != null) {
			int bal = card.getBalance() + StringUtility.removeDollarSign(tokens[2]);
			if (bal <= card.getLimit()) {
				card.setBalance(bal);
			}
		}
	}

	public void creditAmount(String[] tokens) {
		CreditCard card = isCardValid(tokens[1]);
		if (card != null && tokens[2] != null) {
			int bal = card.getBalance() - StringUtility.removeDollarSign(tokens[2]);
			card.setBalance(bal);
		}
	}

	public Map<String, CreditCard> getCards() {
		return holder.getCards();
	}

}
