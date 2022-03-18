package com.tus.usermanagement.DTO;

import java.util.Date;

import com.tus.usermanagement.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransferDTO {

	private int userid;
	private String firstName;
	private String secondName;
	private Date dob;
	private String mobNo;
	private String ageGroup;
	private String emailId;	
	
	public UserTransferDTO(UserEntity user) {
		super();
		this.userid = user.getUserid();
		this.firstName = user.getFirstName();
		this.secondName = user.getSecondName();
		this.dob = user.getDob();
		this.mobNo = user.getMobNo();
		this.ageGroup = user.getAgeGroup();
		this.emailId=user.getEmailId();
	}
}
