package com.tus.SpringEmailClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringEmailClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailClientApplication.class, args);
	}

}
