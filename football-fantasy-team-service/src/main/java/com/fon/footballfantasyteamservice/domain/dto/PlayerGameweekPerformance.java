package com.fon.footballfantasyteamservice.domain.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PlayerGameweekPerformance implements Serializable {
	
	private static final long serialVersionUID = 5407747763074017323L;

	private Long id;
	@JsonIgnoreProperties(value = "playerGameweekPerformances", allowSetters = true)
	private Player player;
	@JsonIgnoreProperties(value = {"matches", "playerGameweekPerformances"}, allowSetters = true)
	private Gameweek gameweek;
	private int points;
	
}
