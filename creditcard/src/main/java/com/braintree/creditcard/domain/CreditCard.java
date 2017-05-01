package com.braintree.creditcard.domain;

import java.sql.Timestamp;

import com.braintree.creditcard.utility.StringUtility;

/**
 * Domain class for CreditCard
 * @author Subashini
 *
 */
public class CreditCard {



	private String id;
	private String name;
	private String cardNum;
	private int limit;
	private int balance;
	private boolean isLuhnValid;
	private Timestamp createdDate;
	private Timestamp updatedDate;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean isLuhnValid() {
		return isLuhnValid;
	}
	public void setLuhnValid(boolean isLuhnValid) {
		this.isLuhnValid = isLuhnValid;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer("CreditCard [id= ");

		strBuff.append(id);
		strBuff.append(" , name= ");
		strBuff.append(name);
		strBuff.append(" , cardNum= ");
		strBuff.append(cardNum);
		strBuff.append(" , limit= ");
		strBuff.append(limit);
		strBuff.append(" , balance= ");
		strBuff.append(balance);
		strBuff.append(" , isLuhnValid= ");
		strBuff.append(isLuhnValid);
		strBuff.append(" , createdDate= ");
		strBuff.append(createdDate);
		strBuff.append(" , updatedDate= ");
		strBuff.append(updatedDate);
		strBuff.append(" ]");
		return strBuff.toString();
	}
	
	
	public String toSummary(){
		StringBuffer strBuff = new StringBuffer(this.getName());
		strBuff.append("\t");
		strBuff.append(" | ");
		if(isLuhnValid){
			strBuff.append(StringUtility.addDollarSign(balance));
		}
		else{
			strBuff.append("error");
		}
		return strBuff.toString();
	}
	/*@Override
	public int compare(CreditCard o1, CreditCard o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());

	}*/
	
	public int compareTo(CreditCard o) {
		// TODO Auto-generated method stub
		return this.getName().compareTo(o.getName());
	}
	
}
