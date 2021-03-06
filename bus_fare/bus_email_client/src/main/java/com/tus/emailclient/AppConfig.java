package com.tus.emailclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class AppConfig {

	@Bean
	@Primary
	public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
		
		FreeMarkerConfigurationFactoryBean bean= new FreeMarkerConfigurationFactoryBean();
		bean.setTemplateLoaderPath("classpath:/template/");
		return bean;
	}
	
}
