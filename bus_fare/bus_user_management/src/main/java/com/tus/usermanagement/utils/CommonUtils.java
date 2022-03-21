

package com.tus.usermanagement.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tus.usermanagement.DTO.EmailDTO;

@Component
public class CommonUtils {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${email-client-service}")
	String emailServiceName;

	public void sendEmailNotification(EmailDTO emailDTO) {
		//String restObj=restTemplate.postForObject("http://email_client/api/v1/send", emailDTO, String.class);
		restTemplate.postForObject("http://email-client/api/v1/send", emailDTO, String.class);
	}
}
