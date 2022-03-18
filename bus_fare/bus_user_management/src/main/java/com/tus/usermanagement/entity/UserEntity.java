package com.tus.usermanagement.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_details",schema = "easyfare")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userid_generator")
	@SequenceGenerator(name="userid_generator",sequenceName = "easyfare.user_detail_seq",allocationSize = 1)
	@Column(name="user_id")
	private int userid;
	@Column(name = "first_name",nullable = false)
	private String firstName;
	@Column(name="second_name",nullable = false)
	private String secondName;
	@Column(name="date_of_birth",nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dob;
	@Column(name="mobile_number",nullable = false)
	private String mobNo;
	@Column(name="age_group",nullable = false)
	private String ageGroup;
	@Column(name="email_id")
	private String emailId;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="smartcard_details_cardid")
	private SmartCardEntity smartCard;
	
	public SmartCardEntity getSmartCard() {
		return smartCard;
	}
	public void setSmartCard(SmartCardEntity smartCard) {
		this.smartCard = smartCard;
	}
	public UserEntity() {
		
	}
	public UserEntity(String firstName, String secondName, Date dob,
			String mobNo, String ageGroup,
			String emailId) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.dob = dob;
		this.mobNo = mobNo;
		this.ageGroup = ageGroup;
		this.emailId = emailId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup=ageGroup;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
