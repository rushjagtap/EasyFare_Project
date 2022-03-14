package com.tus.easyfare.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

	private String name;
	private String toEmail;
	private String subject;
	private String source;
	private String destination;
	private long fare;
	private long availableBalance;
}
