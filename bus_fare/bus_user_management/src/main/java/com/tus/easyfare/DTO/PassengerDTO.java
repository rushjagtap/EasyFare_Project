package com.tus.easyfare.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassengerDTO {
	private Integer userid;
	private String startpoint;
	private String routeNumber;
	private long busNumber;
	private long availableBalance;
		
}
