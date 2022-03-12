package com.tus.EasyFare.DistanceCalculator.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.tus.EasyFare.DistanceCalculator.DTO.DataPointsDTO;

@Service
public class LocationService {
	public String generateReqParams(ArrayList<DataPointsDTO> arrayList) {
//		= "";
//		arrayList.stream().map(i->i.getLatitude()+","+i.getLongitude()+":")
		return arrayList.stream().map(i->i.getLatitude()+","+i.getLongitude()).reduce("",(i,j)->i+":"+j).substring(1);
	}
}
