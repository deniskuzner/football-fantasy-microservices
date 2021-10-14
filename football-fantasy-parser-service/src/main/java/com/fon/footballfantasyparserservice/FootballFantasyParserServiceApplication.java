package com.fon.footballfantasyparserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FootballFantasyParserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballFantasyParserServiceApplication.class, args);
	}

}
