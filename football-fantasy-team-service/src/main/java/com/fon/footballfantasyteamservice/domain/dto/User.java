package com.fon.footballfantasyteamservice.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class User {

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String birthDate;
	private String phoneNumber;
	private Long favouriteClubId;
	
}
