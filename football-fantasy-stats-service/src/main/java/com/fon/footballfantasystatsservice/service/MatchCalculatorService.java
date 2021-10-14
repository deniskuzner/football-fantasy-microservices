package com.fon.footballfantasystatsservice.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fon.footballfantasystatsservice.domain.Match;
import com.fon.footballfantasystatsservice.domain.PlayerGameweekPerformance;

public interface MatchCalculatorService {

	List<PlayerGameweekPerformance> getMatchPerformances(@NotNull Match match);
	
}
