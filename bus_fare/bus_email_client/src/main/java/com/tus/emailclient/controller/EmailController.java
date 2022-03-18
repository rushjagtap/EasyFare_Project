package com.tus.emailclient.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tus.emailclient.DAO.EmailDAO;
import com.tus.emailclient.service.EmailSenderService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RestController
@RequestMapping(value = "/api/v1")
public class EmailController {
	
	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public ResponseEntity<Object> triggerEmail(@RequestBody EmailDAO emailDAO) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		Map<String, Object> templateModel= new HashMap<String, Object>();
		templateModel.put("name", emailDAO.getName());
		templateModel.put("source", emailDAO.getSource());
		templateModel.put("destination", emailDAO.getDestination());
		templateModel.put("fare", emailDAO.getFare());
		templateModel.put("balance", emailDAO.getAvailableBalance());
		if(emailDAO.getAvailableBalance() <= 0) {
			templateModel.put("commemntstart", "");
			templateModel.put("commemntend", "");
		}else {
			templateModel.put("commemntstart", "<!--");
			templateModel.put("commemntend", "-->");
		}
		//emailSenderService.sendMessageUsingThymeleafTemplate(emailDAO, templateModel);
		emailSenderService.sendMail(emailDAO, templateModel);
		System.out.println("Email Send....");
		Map<String, String> respMap= new HashMap<String, String>();
		respMap.put("Message", "Email sent successfully");
		return new ResponseEntity<Object>(respMap, HttpStatus.CREATED);
	}
}
