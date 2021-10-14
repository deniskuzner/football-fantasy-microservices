package com.fon.footballfantasyuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FootballFantasyUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballFantasyUserServiceApplication.class, args);
	}

}
