package com.tus.EasyFare.DistanceCalculator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPointsDTO {
	
	private double latitude;
	private double longitude;
	
	public String getLatitudeString() {
		return Double.toString(latitude);
	}


	
}
