package com.tus.farecalculation.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FareDTO {

	private String source;
	private String destination;
	private double fare;
}
