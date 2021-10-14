package com.fon.footballfantasyteamservice.service;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fon.footballfantasyteamservice.domain.League;
import com.fon.footballfantasyteamservice.domain.TeamLeagueMembership;

public interface TeamLeagueMembershipService {
	
	League save(@NotNull TeamLeagueMembership tlm);

	TeamLeagueMembership findById(@NotNull @Min(1) Long id);

	List<TeamLeagueMembership> findByTeamId(@NotNull @Min(1) Long teamId);
	
	List<TeamLeagueMembership> findByLeagueId(@NotNull @Min(1) Long leagueId);

	void deleteById(@NotNull @Min(1) Long id);

	void deleteByLeagueId(@NotNull @Min(1) Long id);

	void deleteByTeamAndLeague(@NotNull @Min(1) Long teamId, @NotNull @Min(1) Long leagueId);

}
