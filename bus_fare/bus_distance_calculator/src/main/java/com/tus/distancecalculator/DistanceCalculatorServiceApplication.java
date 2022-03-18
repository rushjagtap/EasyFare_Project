package com.tus.distancecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DistanceCalculatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistanceCalculatorServiceApplication.class, args);
	}

}
