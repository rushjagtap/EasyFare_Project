package com.tus.usermanagement.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tus.usermanagement.repository.UserRepository;

@Component
public class UserValidation {

	@Autowired
	UserRepository userRepo;
	
	public boolean validateUserAndBalance(Integer userId) {
		if(userRepo.existsById(userId) && (userRepo.findById(userId).get().getSmartCard().getBalance()>0) && (userRepo.findById(userId).get().getSmartCard().getCardStatus().equalsIgnoreCase("available"))) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateUser(Integer userId) {
		if(userRepo.existsById(userId)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateSmartCardBalance(Integer userId) {
		if(userRepo.findById(userId).get().getSmartCard().getBalance()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean validateSmartCardStatus(Integer userId) {
		if(userRepo.findById(userId).get().getSmartCard().getCardStatus().equalsIgnoreCase("available")) {
			return true;
		}else {
			return false;
		}
	}
}
