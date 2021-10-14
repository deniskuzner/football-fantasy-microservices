package com.fon.footballfantasygateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;
	
	@Bean
	public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
		return http
			.exceptionHandling()
			.authenticationEntryPoint((swe, e) -> {
				return Mono.fromRunnable(() -> {
					swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				});
			}).accessDeniedHandler((swe, e) -> {
				return Mono.fromRunnable(() -> {
					swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
				});
			}).and()
			.csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.authenticationManager(authenticationManager)
			.securityContextRepository(securityContextRepository)
			.authorizeExchange()
			.pathMatchers(HttpMethod.OPTIONS).permitAll()
			.pathMatchers("/login").permitAll()
			.pathMatchers(HttpMethod.POST, "/users/register").permitAll()
			.pathMatchers(HttpMethod.GET, "/clubs/all").permitAll()
			.pathMatchers(HttpMethod.GET, "/clubs/parse-season-clubs").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.POST, "/clubs/parse").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.DELETE, "/clubs/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.GET, "/gameweeks/parse-season-gameweeks").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.DELETE, "/gameweeks/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers("/match-events/parse-match-events/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.DELETE, "/match-events/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers(HttpMethod.DELETE, "/players/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers("/performances/calculate/**").hasAuthority("ROLE_ADMIN")
			.pathMatchers("/team-performances/calculate/**").hasAuthority("ROLE_ADMIN")
			.anyExchange().authenticated()
			.and().build();
	}

}
