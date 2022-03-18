package com.tus.emailclient.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.tus.emailclient.AppConfig;
import com.tus.emailclient.DAO.EmailDAO;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;
	
	@Autowired
	private Configuration configuration;
	
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
	
	 public void sendMail(EmailDAO emailInfo, Map<String, Object> templateModel) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		 MimeMessage message= mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");
		 helper.setTo(emailInfo.getToEmail());
		 Template template= configuration.getTemplate("sample.ftl");
		 String html= FreeMarkerTemplateUtils.processTemplateIntoString(template, templateModel);
		 System.out.println(html);
		 helper.setSubject(emailInfo.getSubject());
		 helper.setText(html, true);
		 Resource resourceFile= new ClassPathResource("/template/bus_logo.png");
		 helper.addInline("logo", resourceFile,"image/png");
//		 System.out.println(helper.ge);
		 mailSender.send(message);
		 
	 }
}
