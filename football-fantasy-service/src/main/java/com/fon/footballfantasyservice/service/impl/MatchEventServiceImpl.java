package com.fon.footballfantasyservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyservice.cloud.ParserClient;
import com.fon.footballfantasyservice.domain.Gameweek;
import com.fon.footballfantasyservice.domain.Match;
import com.fon.footballfantasyservice.domain.Player;
import com.fon.footballfantasyservice.domain.event.Card;
import com.fon.footballfantasyservice.domain.event.Goal;
import com.fon.footballfantasyservice.domain.event.MatchEvent;
import com.fon.footballfantasyservice.domain.event.MinutesPlayed;
import com.fon.footballfantasyservice.domain.event.Substitution;
import com.fon.footballfantasyservice.repository.GameweekRepository;
import com.fon.footballfantasyservice.repository.MatchEventRepository;
import com.fon.footballfantasyservice.service.ClubService;
import com.fon.footballfantasyservice.service.MatchEventService;
import com.fon.footballfantasyservice.service.MatchService;
import com.fon.footballfantasyservice.service.PlayerService;

@Service
@Transactional
@Validated
public class MatchEventServiceImpl implements MatchEventService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchServiceImpl.class);
	
	@Autowired
	MatchEventRepository matchEventRepository;
	
	@Autowired
	GameweekRepository gameweekRepository;
	
	@Autowired
	ParserClient parserClient;
	
	@Autowired
	ClubService clubService;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	PlayerService playerService;

	@Override
	public List<MatchEvent> parseMatchEventsByGameweekId(Long gameweekId) {
		List<MatchEvent> matchEvents = new ArrayList<>();
		Gameweek gameweek = gameweekRepository.findById(gameweekId).get();
		if(gameweek == null) {
			return null;
		}
		for (Match match : gameweek.getMatches()) {
			matchEvents.addAll(parseMatchEventsByMatchUrl(match.getUrl()));
		}
		return matchEvents;
	} 
	
	@Override
	public List<MatchEvent> parseMatchEventsByMatchUrl(String matchUrl) {
		List<MatchEvent> result = new ArrayList<>();
		
		//if match hasn't played yet
		if(matchUrl == null)
			return new ArrayList<>();
		
		// Preventing duplicates
		List<MatchEvent> existingMatchEvents = findByMatchId(matchService.findByUrl(matchUrl).getId());
		if(existingMatchEvents.size() > 0)
			return existingMatchEvents;
		
		List<MatchEvent> matchEvents = parserClient.parseMatchEventsByMatchUrl(matchUrl);
		for (MatchEvent matchEvent : matchEvents) {
			matchEvent = buildMatchEvent(matchEvent, matchUrl);
			if(matchEvent != null) {
				save(matchEvent);
				result.add(matchEvent);
			}
		}
		LOGGER.info("Saved events for match: {}", matchUrl);
		return result;
	}


	@Override
	public MatchEvent save(MatchEvent matchEvent) {
		return matchEventRepository.save(matchEvent);
	}

	@Override
	public List<MatchEvent> findByMatchId(Long matchId) {
		return matchEventRepository.findByMatchId(matchId);
	}
	
	@Override
	public void deleteById(Long id) {
		matchEventRepository.deleteById(id);
	}
	
	private MatchEvent buildMatchEvent(MatchEvent matchEvent, String matchUrl) {
		// Set matchId
		matchEvent.setMatchId(matchService.findByUrl(matchUrl).getId());
		
		// Set club
		matchEvent.setClub(clubService.findByUrl(matchEvent.getClub().getUrl()));
		
		// Event specific fields
		if(matchEvent instanceof MinutesPlayed) {
			MinutesPlayed minutesPlayed = (MinutesPlayed) matchEvent;
			Player player = playerService.findByUrl(minutesPlayed.getPlayer().getUrl());
			if(player == null)
				return null;
			minutesPlayed.setPlayer(player);
			matchEvent = minutesPlayed;
		}
		if(matchEvent instanceof Card) {
			Card card = (Card) matchEvent;
			Player player = playerService.findByUrl(card.getPlayer().getUrl());
			if(player == null)
				return null;
			card.setPlayer(player);
			matchEvent = card;
		}
		if(matchEvent instanceof Goal) {
			Goal goal = (Goal) matchEvent;
			Player player = playerService.findByUrl(goal.getGoalPlayer().getUrl());
			if(player == null)
				return null;
			goal.setGoalPlayer(player);
			matchEvent = goal;
		}
		if(matchEvent instanceof Substitution) {
			Substitution substitution = (Substitution) matchEvent;
			Player inPlayer = playerService.findByUrl(substitution.getInPlayer().getUrl());
			if(inPlayer == null)
				return null;
			substitution.setInPlayer(inPlayer);
			Player outPlayer = playerService.findByUrl(substitution.getOutPlayer().getUrl());
			if(outPlayer == null)
				return null;
			substitution.setOutPlayer(outPlayer);
			matchEvent = substitution;
		}
		return matchEvent;
	}
	
}
