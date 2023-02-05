package com.aent.hotel.service.serviceregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceregistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceregistrationApplication.class, args);
	}

}
