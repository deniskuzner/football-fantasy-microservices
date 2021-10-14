package com.fon.footballfantasyservice.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fon.footballfantasyservice.domain.Match;
import com.fon.footballfantasyservice.service.dto.MatchSearchRequest;

public interface MatchService {

	Match save(Match match);

	Match findById(@NotNull @Min(1) Long id);

	Match findByUrl(@NotNull String url);

	List<Match> findAll();

	void deleteById(@NotNull @Min(1) Long id);
	
	List<Match> searchMatches(@Valid MatchSearchRequest matchSearchRequest);
	
	List<Match> findByGameweekId(@NotNull @Min(1) Long gameweekId);
	
	int updateSent(@NotNull @Min(1) Long id);
	
	List<Match> findByGameweekOrderNumber(@NotNull @Min(1) int gameweekOrderNumber);
	
}
