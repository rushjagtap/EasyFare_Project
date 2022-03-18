package com.tus.distancecalculator.DTO;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopPointsDTO {

	private ArrayList<DataPointsDTO> pointsList;
}
