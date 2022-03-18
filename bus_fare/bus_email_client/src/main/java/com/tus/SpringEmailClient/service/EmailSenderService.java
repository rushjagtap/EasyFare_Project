package com.tus.SpringEmailClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tus.SpringEmailClient.DAO.EmailDAO;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	
	@Value("${fromEmail}")
	private String fromEmail;
	
	public void sendSimpleEmail(EmailDAO emailDAO) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(emailDAO.getToEmail());
		message.setFrom(fromEmail);
		message.setSubject(emailDAO.getSubject());
		double availBalance= emailDAO.getAvailableBalance();
		if(availBalance<=0) {
			message.setText("Hai "+emailDAO.getName()+",\n\nThe fare for last ride from "+emailDAO.getSource()+" to "+emailDAO.getDestination()+" is "+emailDAO.getFare()+" euros.\nThe available balance is "+emailDAO.getAvailableBalance()+".\nPlease recharge soon!!");
		}else {
			message.setText("Hai "+emailDAO.getName()+",\n\nThe fare for last ride from "+emailDAO.getSource()+" to "+emailDAO.getDestination()+" is "+emailDAO.getFare()+" euros.\nThe available balance is "+emailDAO.getAvailableBalance());
		}
		mailSender.send(message);
		System.out.println("Mail Send...");
	}
}
