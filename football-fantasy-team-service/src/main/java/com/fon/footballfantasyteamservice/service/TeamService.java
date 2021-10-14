package com.fon.footballfantasyteamservice.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fon.footballfantasyteamservice.domain.Team;

public interface TeamService {
	
	Team save(@NotNull Team team);
	
	Team update(@NotNull Team team);
	
	List<Team> findAll();
	
	Team findById(@NotNull @Min(1) Long id);
	
	Team findByUserId(@NotNull @Min(1) Long userId);
	
	List<Team> findByLeagueId(@NotNull @Min(1) Long leagueId);
	
	void deleteById(@NotNull @Min(1) Long id);
	
	void deleteByUserId(@NotNull @Min(1) Long userId);

	Long findIdByUserId(@NotNull @Min(1) Long userId);

}
