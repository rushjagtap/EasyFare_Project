package com.tus.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tus.usermanagement.DTO.PassengerDTO;
import com.tus.usermanagement.DTO.TapDTO;
import com.tus.usermanagement.entity.UserEntity;
import com.tus.usermanagement.repository.UserRepository;

@Service
public class TapService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${fleet-management-service}")
	String fleetService;
	
	public String tapUser(TapDTO tappedUser) {
		UserEntity userDetails=userRepo.findById(tappedUser.getUserId()).get();
		long availableBalance=userDetails.getSmartCard().getBalance();
		PassengerDTO passObj= new PassengerDTO(tappedUser.getUserId(), tappedUser.getSourcePoint(), tappedUser.getRouteNumber(), tappedUser.getBusNumber(), availableBalance);
		String restObj=restTemplate.postForObject("http://"+fleetService+"/api/v1/passenger", passObj, String.class);
		System.out.println(restObj+" Call successfull");
		return restObj;
	}
}
