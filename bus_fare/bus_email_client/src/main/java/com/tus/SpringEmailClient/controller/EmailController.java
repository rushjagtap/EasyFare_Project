package com.tus.SpringEmailClient.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tus.SpringEmailClient.DAO.EmailDAO;
import com.tus.SpringEmailClient.service.EmailSenderService;

@RestController
@RequestMapping(value = "/api/v1")
public class EmailController {
	
	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public ResponseEntity<Object> triggerEmail(@RequestBody EmailDAO emailDAO) {
		emailSenderService.sendSimpleEmail(emailDAO);
		System.out.println("Email Send....");
		Map<String, String> respMap= new HashMap<String, String>();
		respMap.put("Message", "Email sent successfully");
		return new ResponseEntity<Object>(respMap, HttpStatus.CREATED);
	}
}
