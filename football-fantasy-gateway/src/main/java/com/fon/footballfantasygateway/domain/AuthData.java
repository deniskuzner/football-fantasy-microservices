package com.fon.footballfantasygateway.domain;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AuthData {
	
	private Long userId;
	private Long teamId;
	private String roles;

}
