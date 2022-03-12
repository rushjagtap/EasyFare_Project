package com.tus.easyfare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tus.easyfare.DTO.PassengerDTO;
import com.tus.easyfare.DTO.TapDTO;
import com.tus.easyfare.entity.UserEntity;
import com.tus.easyfare.repository.UserRepository;

@Service
public class TapService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RestTemplate restTemplate;

	public boolean validateUserAndBalance(Integer userId) {
		if(userRepo.existsById(userId) && (userRepo.findById(userId).get().getSmartCard().getBalance()>0) && (userRepo.findById(userId).get().getSmartCard().getCardStatus().equalsIgnoreCase("available"))) {
			return true;
		}else {
			return false;
		}
	}
	
	public String tapUser(TapDTO tappedUser) {
		UserEntity userDetails=userRepo.findById(tappedUser.getUserId()).get();
		long availableBalance=userDetails.getSmartCard().getBalance();
		PassengerDTO passObj= new PassengerDTO(tappedUser.getUserId(), tappedUser.getSourcePoint(), tappedUser.getRouteNumber(), tappedUser.getBusNumber(), availableBalance);
		String restObj=restTemplate.postForObject("http://localhost:8082/api/v1/passenger", passObj, String.class);
		System.out.println(restObj+" Call successfull");
		return restObj;
	}
}
