package com.tus.easyfare.DTO;

import lombok.Data;

@Data
public class TapDTO {

	private Integer userId;
	private String sourcePoint;
	private String routeNumber;
	private long busNumber;
	
}
