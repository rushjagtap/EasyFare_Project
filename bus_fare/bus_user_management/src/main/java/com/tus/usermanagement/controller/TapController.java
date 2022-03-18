package com.tus.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.tus.usermanagement.DTO.ResponseDTO;
import com.tus.usermanagement.DTO.TapDTO;
import com.tus.usermanagement.exception.UserNotFoundException;
import com.tus.usermanagement.service.TapService;
import com.tus.usermanagement.validation.UserValidation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TapController {
	@Autowired
	TapService tapService;
	
	@Autowired
	WebClient.Builder webclientBuilder;
	
	@Autowired
	UserValidation userValidattion;
	
	@RequestMapping(value = "/tap",method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> tapCard(@RequestBody TapDTO tappedUser) {
		Integer userId=tappedUser.getUserId();
		System.out.println("UserId:"+userId);
		ResponseDTO respObj;
		String resp;
		if(userValidattion.validateUserAndBalance(userId)) {
			System.out.println("Validation successful");
			resp= tapService.tapUser(tappedUser);
		}else {
			throw new UserNotFoundException(userId);
		}
		respObj= new ResponseDTO(resp);
		return new ResponseEntity<ResponseDTO>(respObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object getLocationData(){

	    Object o = webclientBuilder.build()
	            .get()
	            .uri("https://api.tomtom.com/routing/1/calculateRoute/52.50931,13.42936:52.50274,13.43872/json?key=HFIEN2N8PyYo32M6zjeAEgRmQyrLQAAl")
	            .retrieve()
	            .bodyToMono(Object.class)
	            .block();
	    return o;
	}
}
