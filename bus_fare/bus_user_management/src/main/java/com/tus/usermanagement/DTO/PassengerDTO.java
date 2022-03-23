package com.tus.usermanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassengerDTO {
	private Integer userid;
	private String startpoint;
	private String routeNumber;
	private String busNumber;
	private double availableBalance;
		
}
