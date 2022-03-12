package com.tus.EasyFare.FleetManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FleetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetManagementApplication.class, args);
	}

}
