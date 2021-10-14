package com.fon.footballfantasyteamservice.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Gameweek implements Serializable {

	private static final long serialVersionUID = -2505204007816968035L;

	private Long id;
	private int orderNumber;
	
}
