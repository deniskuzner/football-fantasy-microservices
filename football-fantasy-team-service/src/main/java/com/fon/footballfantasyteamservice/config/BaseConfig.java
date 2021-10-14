package com.fon.footballfantasyteamservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fon.monitoring.aspect.MonitoringAspect;

@Configuration
@EnableFeignClients(basePackages = "com.fon.footballfantasyteamservice.cloud")
public class BaseConfig {

	@Bean
	public MonitoringAspect performanceMonitoringAspect() {
		return new MonitoringAspect();
	}
	
}
