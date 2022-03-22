package com.tus.farecalculation.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopPointsDTO {

	private ArrayList<DataPointsDTO> pointsList;

}
