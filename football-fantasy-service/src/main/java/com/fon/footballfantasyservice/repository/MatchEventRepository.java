package com.fon.footballfantasyservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fon.footballfantasyservice.domain.event.MatchEvent;

public interface MatchEventRepository extends CrudRepository<MatchEvent, Long> {
	
	List<MatchEvent> findByMatchId(Long matchId);

}
