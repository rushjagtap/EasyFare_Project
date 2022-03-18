package com.tus.distancecalculator;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class DistanceCalculatorConfig {
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
	    return WebClient.builder();
	}
}
