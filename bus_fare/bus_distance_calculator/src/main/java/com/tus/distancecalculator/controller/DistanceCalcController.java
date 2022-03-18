package com.tus.distancecalculator.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.tus.distancecalculator.DTO.ResponseDTO;
import com.tus.distancecalculator.DTO.StopPointsDTO;
import com.tus.distancecalculator.VO.Root;
import com.tus.distancecalculator.VO.Route;
import com.tus.distancecalculator.service.LocationService;

@RestController
@RequestMapping("/api/v1")
public class DistanceCalcController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	WebClient.Builder webclientBuilder;
	
	@Value("${authKey}")
	String authKey;

	@Value("${travelMode}")
	String travelMode;
	
	@Value("${locationUrl}")
	String locationUrl;
	
	@RequestMapping(value = "/locations",method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> getDistance(@RequestBody StopPointsDTO locations) {
		//System.out.println("locations"+locations.getPointsList());
		String reqParam=locationService.generateReqParams(locations.getPointsList());
		System.out.println("reqParam "+reqParam);
		String url=locationUrl+reqParam+"/json?travelMode="+travelMode+"&key="+authKey;
		System.out.println("url: "+url);
		Root root = webclientBuilder.build()
	            .get()
	            .uri(url)
	            .retrieve()
	            .bodyToMono(Root.class)
	            .block();
		Double ditanceInKms=(root.getRoutes().get(0).getSummary().lengthInMeters)/1000;
		System.out.println("Distance travelled is : "+ditanceInKms+" KM");
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(ditanceInKms), HttpStatus.FOUND);
	}
}
