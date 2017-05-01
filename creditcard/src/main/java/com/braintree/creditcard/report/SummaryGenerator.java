package com.braintree.creditcard.report;

import java.util.Map;

import com.braintree.creditcard.domain.CreditCard;

/**
 * This class is used to generate the summary report. 
 * default is natural ordering
 * 
 * @author Subashini
 *
 */
public class SummaryGenerator {

	public String generateSummarybyName(Map<String, CreditCard> cards) {
		StringBuffer strBuffer = new StringBuffer();

		if(cards!=null && cards.size()>0){
		strBuffer.append("--------------------------------------------------------------\n");
		strBuffer.append("\t\t $$$$$$$$$ Summary Generator $$$$$$$$\t\t\n");
		strBuffer.append("--------------------------------------------------------------\n");
		// add headers
		strBuffer.append("Name");
		strBuffer.append("\t");
		strBuffer.append(" | ");
		strBuffer.append("Balance\n");
		strBuffer.append("--------------------------------------------------------------\n");

		for (String key : cards.keySet()) {
//			System.out.println(key);
			CreditCard c = cards.get(key);
//			System.out.println(c.toSummary());
			strBuffer.append(c.toSummary());
			strBuffer.append("\n");
			
		}
		strBuffer.append("--------------------------------------------------------------");
		}
		return strBuffer.toString();
	}

	/*
	 * public String generateSummarybyLimit(Map<String, CreditCard> cards){
	 * StringBuffer strBuffer = new
	 * StringBuffer("********SUmmary Generator by Values sort using comparator***********"
	 * ); //add headers // List<CreditCard> values =
	 * (List<CreditCard>)cards.values()///; List<CreditCard> values = new
	 * ArrayList<CreditCard>(cards.values());
	 * Collections.sort((List<CreditCard>) values); // orders it in lexical
	 * order - for other ordering, we can use comparator for(CreditCard cc :
	 * values){ strBuffer.append(cc.toSummary()); strBuffer.append("\n"); }
	 * System.out.println(strBuffer.toString());
	 * 
	 * 
	 * Collections.sort(values, new Comparator<CreditCard>() { public int
	 * compare(CreditCard card1, CreditCard card2){ Integer limit1 = new
	 * Integer(card1.getLimit()); Integer limit2 = new
	 * Integer(card2.getLimit());
	 * 
	 * return limit1.compareTo(limit2);
	 * 
	 * } }); StringBuffer sBuff = new
	 * StringBuffer("***********SUmmary Generator by Limits sort using comparator*********"
	 * ); for(CreditCard cc : values){ sBuff.append(cc.toSummary());
	 * sBuff.append("\n"); } System.out.println(sBuff.toString()); for(Object
	 * key : keys){ strBuffer.append(cards.get(key).toSummary());
	 * strBuffer.append("\n"); } return sBuff.toString(); }
	 */

}
