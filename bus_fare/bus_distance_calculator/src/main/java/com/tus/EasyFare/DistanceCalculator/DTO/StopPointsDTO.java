package com.tus.EasyFare.DistanceCalculator.DTO;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopPointsDTO {

	private ArrayList<DataPointsDTO> pointsList;

}
