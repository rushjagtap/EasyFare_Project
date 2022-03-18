package com.tus.easyfare.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tus.easyfare.DTO.EmailDTO;

@Component
public class CommonUtils {
	
	@Autowired
	RestTemplate restTemplate;

	public void sendEmailNotification(EmailDTO emailDTO) {
		//String restObj=restTemplate.postForObject("http://email_client/api/v1/send", emailDTO, String.class);
		restTemplate.postForObject("http://localhost:8086/api/v1/send", emailDTO, String.class);
	}
}
