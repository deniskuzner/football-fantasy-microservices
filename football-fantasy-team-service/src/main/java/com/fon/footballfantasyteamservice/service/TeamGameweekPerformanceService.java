package com.fon.footballfantasyteamservice.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fon.footballfantasyteamservice.domain.Team;
import com.fon.footballfantasyteamservice.service.dto.TeamGameweekStats;

public interface TeamGameweekPerformanceService {
	
	List<Team> calculateGameweekPoints(@NotNull @Min(1) int gameweekOrderNumber);
	
	TeamGameweekStats getGameweekStats(@NotNull @Min(1) Long teamId, @NotNull @Min(1) Long gameweekId);
	
}
