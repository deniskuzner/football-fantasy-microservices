package com.fon.footballfantasystatsservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fon.footballfantasystatsservice.domain.event.MatchEvent;
import com.fon.footballfantasystatsservice.util.MatchEventDeserializer;

@Configuration
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
