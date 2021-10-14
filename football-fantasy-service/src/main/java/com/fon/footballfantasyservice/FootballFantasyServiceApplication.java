package com.fon.footballfantasyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FootballFantasyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballFantasyServiceApplication.class, args);
	}

}
