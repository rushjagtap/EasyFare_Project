package com.tus.usermanagement.entity;

import java.io.Serializable;

public class SmartCardKey implements Serializable{

	private int cardId;
	private String cardNum;
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
}
