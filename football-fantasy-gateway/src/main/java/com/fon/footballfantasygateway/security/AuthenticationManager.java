package com.fon.footballfantasygateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
	
	@Autowired
	UserPrincipalService userPrincipalService;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String token = authentication.getCredentials().toString();

		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token);
		String username = decodedJWT.getSubject();

		if (username != null) {
			UserPrincipal principal = (UserPrincipal) userPrincipalService.loadUserByUsername(username);
			Authentication auth = new UsernamePasswordAuthenticationToken(username, null, principal.getAuthorities());
			return Mono.just(auth);
		}
		return Mono.empty();
	}

}
