package com.fon.footballfantasyteamservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasyteamservice.domain.TeamPlayer;

public interface TeamPlayerRepository extends CrudRepository<TeamPlayer, Long> {
	
	long deleteByTeamId(Long teamId);

}
