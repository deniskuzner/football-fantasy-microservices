package com.fon.footballfantasygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/clubs/**", "/gameweeks/**", "/matches/**", "/match-events/**", "/players/**", "/performances/**")
						.uri("lb://football-fantasy-service"))
				.route(r -> r.path("/parser/**").uri("lb://football-fantasy-parser-service"))
				.route(r -> r.path("/stats/**").uri("lb://football-fantasy-stats-service"))
				.route(r -> r.path("/leagues/**", "/teams/**", "/team-performances/**", "/team-league-memberships/**")
						.uri("lb://football-fantasy-team-service"))
				.route(r -> r.path("/users/**", "/login").uri("lb://football-fantasy-user-service"))
				.build();
	}

}
