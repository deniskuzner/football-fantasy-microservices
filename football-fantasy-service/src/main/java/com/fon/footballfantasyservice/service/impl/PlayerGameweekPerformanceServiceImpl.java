package com.fon.footballfantasyservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyservice.cloud.StatsClient;
import com.fon.footballfantasyservice.domain.Gameweek;
import com.fon.footballfantasyservice.domain.Match;
import com.fon.footballfantasyservice.domain.Player;
import com.fon.footballfantasyservice.domain.PlayerGameweekPerformance;
import com.fon.footballfantasyservice.repository.PlayerGameweekPerformanceRepository;
import com.fon.footballfantasyservice.repository.PlayerRepository;
import com.fon.footballfantasyservice.service.GameweekService;
import com.fon.footballfantasyservice.service.MatchService;
import com.fon.footballfantasyservice.service.PlayerGameweekPerformanceService;
import com.fon.footballfantasyservice.service.dto.MatchSearchRequest;

@Service
@Transactional
@Validated
public class PlayerGameweekPerformanceServiceImpl implements PlayerGameweekPerformanceService {
	
	@Autowired
	PlayerGameweekPerformanceRepository performanceRepository;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	GameweekService gameweekService;
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	StatsClient statsClient;
	
	@Override
	public List<PlayerGameweekPerformance> calculateByDate(MatchSearchRequest searchRequest) {
		List<PlayerGameweekPerformance> performances = new ArrayList<>();
		List<Match> matches = matchService.searchMatches(searchRequest);
		for (Match match : matches) {
			if(match.getEvents().size() == 0)
				continue;
			if(matchService.updateSent(match.getId()) == 1) {
				List<PlayerGameweekPerformance> matchPerformances = statsClient.getMatchPerformances(match);
				performances.addAll(saveAll(matchPerformances));
			}
		}
		return performances;
	}

	@Override
	public List<PlayerGameweekPerformance> calculateByGameweek(int gameweekOrderNumber) {
		List<PlayerGameweekPerformance> performances = new ArrayList<>();
		Gameweek gameweek = gameweekService.findByOrderNumber(gameweekOrderNumber);
		List<Match> matches = matchService.findByGameweekId(gameweek.getId());
		for (Match match : matches) {
			if(match.getEvents().size() == 0)
				continue;
			if(matchService.updateSent(match.getId()) == 1) {
				List<PlayerGameweekPerformance> matchPerformances = statsClient.getMatchPerformances(match);
				performances.addAll(saveAll(matchPerformances));
			}
		}
		return performances;
	}

	@Override
	public List<PlayerGameweekPerformance> saveAll(List<PlayerGameweekPerformance> playerGameweekPerformances) {
		for (PlayerGameweekPerformance pgp : playerGameweekPerformances) {
			Player player = pgp.getPlayer();
			
			// update player total points and price
			int totalPoints = player.getTotalPoints() + pgp.getPoints();
			double price = statsClient.getUpdatedPrice(pgp);
			playerRepository.updateTotalPointsAndPrice(player.getId(), totalPoints, price);
			
			performanceRepository.save(pgp);
		}
		return playerGameweekPerformances;
	}

	@Override
	public List<PlayerGameweekPerformance> findByPlayerId(Long playerId) {
		return performanceRepository.findByPlayerId(playerId);
	}

	@Override
	public Long countByGameweekId(Long gameweekId) {
		return performanceRepository.countByGameweekId(gameweekId);
	}

}
