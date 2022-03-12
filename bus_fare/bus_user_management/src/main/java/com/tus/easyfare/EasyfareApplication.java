package com.tus.easyfare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EasyfareApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyfareApplication.class, args);
	}

}
