package com.fon.footballfantasyteamservice.service.impl;

import static com.fon.footballfantasyteamservice.exception.TeamGameweekPerformanceException.TeamGameweekPerformanceExceptionCode.PLAYER_POINTS_NOT_CALCULATED;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyteamservice.cloud.FootballFantasyClient;
import com.fon.footballfantasyteamservice.domain.Team;
import com.fon.footballfantasyteamservice.domain.TeamGameweekPerformance;
import com.fon.footballfantasyteamservice.domain.TeamPlayer;
import com.fon.footballfantasyteamservice.domain.dto.PlayerGameweekPerformance;
import com.fon.footballfantasyteamservice.exception.TeamGameweekPerformanceException;
import com.fon.footballfantasyteamservice.repository.TeamGameweekPerformanceRepository;
import com.fon.footballfantasyteamservice.service.TeamGameweekPerformanceService;
import com.fon.footballfantasyteamservice.service.TeamService;
import com.fon.footballfantasyteamservice.service.dto.TeamGameweekStats;

@Service
@Transactional
@Validated
public class TeamGameweekPerformanceServiceImpl implements TeamGameweekPerformanceService {

	@Autowired
	TeamService teamService;

	@Autowired
	TeamGameweekPerformanceRepository tgpRepository;

	@Autowired
	FootballFantasyClient footballFantasyClient;

	@Override
	public List<Team> calculateGameweekPoints(int gameweekOrderNumber) {
		Long gameweekId = footballFantasyClient.findIdByOrderNumber(gameweekOrderNumber);
		// check if player points are calculated for that gameweek
		if (footballFantasyClient.countByGameweekId(gameweekId) == 0) {
			throw new TeamGameweekPerformanceException(PLAYER_POINTS_NOT_CALCULATED, "Player points for gameweek: %s are not calculated!", gameweekId);
		}

		List<Team> teams = (List<Team>) teamService.findAll();
		for (Team team : teams) {
			calculateTeamGameweekPoints(team, gameweekId);
		}

		return teams;
	}
	
	public Team calculateTeamGameweekPoints(Team team, Long gameweekId) {
		TeamGameweekPerformance tgp = TeamGameweekPerformance.builder().team(team)
				.gameweekId(gameweekId).build();
		
		// check if team points are calculated for that gameweek
		TeamGameweekPerformance teamPerformance = tgpRepository.findByTeamAndGameweekId(team, gameweekId);
		int currentGWPoints = 0;
		if (teamPerformance != null) {
			tgp.setId(teamPerformance.getId());
			tgp.setCreatedOn(teamPerformance.getCreatedOn());
			currentGWPoints = teamPerformance.getPoints();
		}

		List<TeamPlayer> players = team.getTeamPlayers();
		for (TeamPlayer tp : players) {
			
			//TODO: UZETI PREKO FEIGN CLIENTA
			if(tp.getPlayer() == null) {
				System.out.println("PLAYER NULL");
			}
			if(tp.getPlayer().getPlayerGameweekPerformances() == null) {
				System.out.println("PGP NULL");
			}
			if(tp.getPlayer().getPlayerGameweekPerformances().isEmpty()) {
				System.out.println("PGP EMPTY");
			}
			Optional<PlayerGameweekPerformance> pgp = tp.getPlayer().getPlayerGameweekPerformances().stream()
					.filter(p -> p.getGameweek().getId().equals(gameweekId)).findFirst();
			if(pgp.isPresent()) {
				tp.setPoints(pgp.get().getPoints());
			} else {
				tp.setPoints(0);
			}
		}
		// set captain and vice captain points
		TeamPlayer captain = team.getTeamPlayers().stream().filter(p -> p.getPlayer().getId().equals(team.getCaptainId())).findFirst().get();
		TeamPlayer viceCaptain = team.getTeamPlayers().stream().filter(p -> p.getPlayer().getId().equals(team.getViceCaptainId())).findFirst().get();
		captain.setPoints(captain.getPoints() * 2);
		viceCaptain.setPoints(captain.getPoints() > 0 ? viceCaptain.getPoints() : viceCaptain.getPoints() * 2);
		
		int totalGameweekPoints = players.stream().filter(p -> !p.isOnBench()).mapToInt(p -> p.getPoints()).sum();
		int newPoints = totalGameweekPoints - currentGWPoints;
		team.setTotalPoints(team.getTotalPoints() + newPoints);
		
		tgp.setPoints(totalGameweekPoints);
		tgpRepository.save(tgp);			
		
		return team;
	}

	@Override
	public TeamGameweekStats getGameweekStats(Long teamId, Long gameweekId) {
		Object[] stats = tgpRepository.getGameweekStats(gameweekId).get(0);
		int averagePoints = ((BigDecimal) stats[0]).intValue();
		int highestPoints = (int) stats[1];
		int rank = ((BigInteger) tgpRepository.getRank(teamId, gameweekId).get(0)[0]).intValue();
		return TeamGameweekStats.builder().averagePoints(averagePoints).highestPoints(highestPoints).rank(rank).build();
	}
	
}
