package com.tus.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.tus.usermanagement.DTO.PassengerDTO;
import com.tus.usermanagement.DTO.ResponseDTO;
import com.tus.usermanagement.DTO.TapDTO;
import com.tus.usermanagement.DTO.TestDTO;
import com.tus.usermanagement.exception.UserNotFoundException;
import com.tus.usermanagement.service.TapService;
import com.tus.usermanagement.validation.UserValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

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

		/*
		 * Object o = webclientBuilder.build() .get() .uri(
		 * "https://api.tomtom.com/routing/1/calculateRoute/52.50931,13.42936:52.50274,13.43872/json?key=HFIEN2N8PyYo32M6zjeAEgRmQyrLQAAl")
		 * .retrieve() .bodyToMono(Object.class) .block(); return o;
		 */
		//TestDTO testDTO= new TestDTO(101, 4, 2020, 9, 6, 11);
	
		
		
		/*
		 * OkHttpClient client = new OkHttpClient(); RequestBody body =
		 * RequestBody.create(JSON,jsonString); Request request = new Request.Builder()
		 * .url(Peakurl) .post(body) .build(); Response response =
		 * client.newCall(request).execute();
		 */
		
	    
	    RestTemplate restTemplate= new RestTemplate();
	    TestDTO testDTO= new TestDTO(101, 4, 2020, 9, 6, 11);
	    String result=restTemplate.postForObject("http://54.227.57.147:5000/predictpeaktime", testDTO, String.class);
	    /*HttpHeaders reqHeader= new HttpHeaders();
		reqHeader.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<TestDTO> reqEntity= new HttpEntity<>(testDTO,reqHeader);
	    */
	    //ResponseEntity<String> respEntity=restTemplate.exchange("http://54.227.57.147:5000/predictpeaktime",HttpMethod.POST,reqEntity,String.class);
	    //restObj.postForObject(url, request, responseType)
	    System.out.println("resposne is:"+result);
	    return null;
	}
}

