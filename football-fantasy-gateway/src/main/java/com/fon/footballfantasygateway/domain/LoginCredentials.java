package com.fon.footballfantasygateway.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class LoginCredentials {
	
	private String username;
	private String password;

}
