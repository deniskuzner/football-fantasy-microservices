package com.fon.footballfantasyteamservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fon.footballfantasyteamservice.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

	Team findByUserId(Long userId);
	
	void deleteByUserId(Long userId);
	
	@Query(value = "select id from teams where user_id = :userId", nativeQuery = true)
	Long findIdByUserId(@Param("userId") Long userId);
	
}
