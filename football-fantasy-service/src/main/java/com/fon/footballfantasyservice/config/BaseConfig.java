package com.fon.footballfantasyservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fon.footballfantasyservice.domain.event.MatchEvent;
import com.fon.footballfantasyservice.util.MatchEventDeserializer;

@Configuration
@EnableFeignClients(basePackages = "com.fon.footballfantasyservice.cloud")
public class BaseConfig {

	@Bean
	public ObjectMapper objectMapper() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(MatchEvent.class, new MatchEventDeserializer());
		return new ObjectMapper()
				.registerModule(module)
				.registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
}
