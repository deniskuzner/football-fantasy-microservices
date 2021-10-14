package com.fon.footballfantasyparserservice.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class Gameweek implements Serializable {

	private static final long serialVersionUID = -2505204007816968035L;

	private int orderNumber;
	@JsonIgnoreProperties(value = "gameweek", allowSetters = true)
	private List<Match> matches;
//	private List<PlayerGameweekPerformance> playerGameweekPerformances;
	
}
