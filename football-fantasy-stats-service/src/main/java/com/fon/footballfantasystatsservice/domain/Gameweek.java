package com.fon.footballfantasystatsservice.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gameweek implements Serializable {

	private static final long serialVersionUID = -2505204007816968035L;

	private Long id;
	private int orderNumber;
	private List<Match> matches;
	@JsonIgnoreProperties(value = "gameweek", allowSetters = true)
	private List<PlayerGameweekPerformance> playerGameweekPerformances;
	
}
