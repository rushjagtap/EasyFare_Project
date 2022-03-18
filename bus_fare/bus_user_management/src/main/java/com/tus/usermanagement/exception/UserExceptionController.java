package com.tus.usermanagement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tus.usermanagement.repository.UserRepository;
import com.tus.usermanagement.validation.UserValidation;

@ControllerAdvice
public class UserExceptionController {
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	UserValidation userValidation;
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<Object> exception(UserNotFoundException exception){
		Map<String, String> response= new HashMap<>();
		String message;
		System.out.println("userId in exception class : "+exception.getUserId());
		if(!userValidation.validateUser(exception.getUserId())) {
			message="User not present";
		}
		else if (!userValidation.validateSmartCardBalance(exception.getUserId())) {
			message="No sufficient balance in smartcard. Please recharge!!";
		}else {
			message="User smartcard is not active";
		}
		response.put("Message", message);
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=PaymentFailedException.class)
	public ResponseEntity<Object> exception(PaymentFailedException exception){
		Map<String, String> response= new HashMap<>();
		String message="Payment failed! No enough balance. Please recharge";
		response.put("Message", message);
		return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);
	}
}
