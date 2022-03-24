package com.tus.FleetManagement.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RouteExceptionController {

	@ExceptionHandler(value=RouteExistException.class)
	public ResponseEntity<Object> routeAlreadyPresent(){
		Map<String, String> response= new HashMap<>();
		response.put("Message", "Route and bus number already present");
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
