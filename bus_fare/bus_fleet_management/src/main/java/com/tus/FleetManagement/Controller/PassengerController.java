package com.tus.FleetManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tus.FleetManagement.DTO.PassengerDTO;

@RestController
@RequestMapping(path = "/api/v1")
public class PassengerController {
	
	@Autowired
	PassengerService passService;

	@RequestMapping(value = "/passenger")
	public ResponseEntity<String> checkPassengerDetails(@RequestBody PassengerDTO passenger) {
		String result=passService.checkPassenger(passenger);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
}
