package com.tus.usermanagement.entity;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@IdClass(SmartCardKey.class)
@Table(schema = "easyfare",name = "smartcard_details")
@Builder
@AllArgsConstructor
public class SmartCardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cardid_generator")
	@SequenceGenerator(name = "cardid_generator",sequenceName = "easyfare.cardid_seq",allocationSize = 1)
	@Column(name="cardid")
	private int cardId;
	//@Id
	@Column(name="smart_card_num")
	private String cardNum;
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name="date_of_reg")
	private Date dateOfReg;
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name="date_of_exp")
	private Date dateOfExp;
	@Column(name="balance")
	private double balance;
	@Column(name = "card_status")
	private String cardStatus;
	
	public SmartCardEntity() {
	
	}
	public SmartCardEntity(String cardNum, Date dateOfReg,
			Date dateOfExp, double balance, String cardStatus) {
		super();
		this.cardNum = cardNum;
		this.dateOfReg = dateOfReg;
		this.dateOfExp = dateOfExp;
		this.balance = balance;
		this.cardStatus = cardStatus;
	}
	
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
	public Date getDateOfReg() {
		return dateOfReg;
	}
	public void setDateOfReg(Date dateOfReg) {
		this.dateOfReg = dateOfReg;
	}
	public Date getDateOfExp() {
		return dateOfExp;
	}
	public void setDateOfExp(Date dateOfExp) {
		this.dateOfExp = dateOfExp;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	@Override
	public String toString() {
		return "SmartCardEntity [cardId=" + cardId + ", cardNum=" + cardNum + ", dateOfReg=" + dateOfReg
				+ ", dateOfExp=" + dateOfExp + ", balance=" + balance + ", cardStatus=" + cardStatus + "]";
	}
	
	
	
}
