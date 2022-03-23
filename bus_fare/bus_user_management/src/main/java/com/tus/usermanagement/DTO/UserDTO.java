package com.tus.usermanagement.DTO;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class UserDTO {
	
	@NotBlank(message = "firstname is mandatory")
	private String firstName;
	private String secondName;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dob;
	@Size(max = 10,min = 10, message = "mobile number must be 10 digits")
	private String mobNo;
	@Email(message = "not a valid email id")
	private String emailId;
	@Size(min = 14,max = 14,message = "not a valid card number")
	private String cardNumber;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateOfReg;
	private double balance;
	private String cardStatus;	
}
