package com.tus.usermanagement.DTO;

import java.sql.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
//@AllArgsConstructor
public class SmartCardDTO {

	@Size(min = 14,max = 14,message = "not a valid card number")
	private String cardNum;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateOfReg;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dateOfExp;
	private double balance;
	private String cardStatus;
		
}
