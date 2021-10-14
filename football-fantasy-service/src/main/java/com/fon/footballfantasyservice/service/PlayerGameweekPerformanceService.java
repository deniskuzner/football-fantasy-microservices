package com.fon.footballfantasyservice.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fon.footballfantasyservice.domain.PlayerGameweekPerformance;
import com.fon.footballfantasyservice.service.dto.MatchSearchRequest;

public interface PlayerGameweekPerformanceService {
	
	List<PlayerGameweekPerformance> calculateByDate(MatchSearchRequest searchRequest);
	
	List<PlayerGameweekPerformance> calculateByGameweek(@NotNull @Min(1) int gameweekOrderNumber);
	
	List<PlayerGameweekPerformance> saveAll(List<PlayerGameweekPerformance> playerGameweekPerformances);

	List<PlayerGameweekPerformance> findByPlayerId(@NotNull @Min(1) Long playerId);

	Long countByGameweekId(@NotNull @Min(1) Long gameweekId);

}
