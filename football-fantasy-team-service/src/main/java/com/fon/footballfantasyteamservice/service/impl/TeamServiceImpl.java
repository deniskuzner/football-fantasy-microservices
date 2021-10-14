package com.fon.footballfantasyteamservice.service.impl;

import static com.fon.footballfantasyteamservice.exception.TeamException.TeamExceptionCode.TEAM_NOT_VALID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fon.footballfantasyteamservice.cloud.FootballFantasyClient;
import com.fon.footballfantasyteamservice.cloud.FootballFantasyUserClient;
import com.fon.footballfantasyteamservice.domain.Team;
import com.fon.footballfantasyteamservice.domain.TeamGameweekPerformance;
import com.fon.footballfantasyteamservice.domain.TeamLeagueMembership;
import com.fon.footballfantasyteamservice.domain.TeamPlayer;
import com.fon.footballfantasyteamservice.domain.dto.Club;
import com.fon.footballfantasyteamservice.domain.dto.Gameweek;
import com.fon.footballfantasyteamservice.domain.dto.Player;
import com.fon.footballfantasyteamservice.domain.dto.User;
import com.fon.footballfantasyteamservice.exception.TeamException;
import com.fon.footballfantasyteamservice.repository.TeamGameweekPerformanceRepository;
import com.fon.footballfantasyteamservice.repository.TeamPlayerRepository;
import com.fon.footballfantasyteamservice.repository.TeamRepository;
import com.fon.footballfantasyteamservice.service.TeamLeagueMembershipService;
import com.fon.footballfantasyteamservice.service.TeamService;

@Service
@Transactional
@Validated
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	TeamPlayerRepository teamPlayerRepository;

	@Autowired
	TeamGameweekPerformanceRepository tgpRepository;

	@Autowired
	TeamLeagueMembershipService tlmService;
	
	@Autowired
	FootballFantasyClient footballFantasyClient;
	
	@Autowired
	FootballFantasyUserClient footballFantasyUserClient;

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamService.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Team save(Team team) {
		validateTeam(team);
		try {
			LOGGER.info(mapper.writeValueAsString(team));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(team.getId() != null) {
			teamPlayerRepository.deleteByTeamId(team.getId());
			team.setModifiedOn(null);
		}
		for (TeamPlayer tp : team.getTeamPlayers()) {
			tp.setTeam(team);
			tp.setPlayerId(tp.getPlayer().getId());
		}
		team.setUserId(team.getUser().getId());
		return teamRepository.save(team);
	}
	
	@Override
	public Team update(Team team) {
		validateTeam(team);
		if(team.getId() != null) {
			team.setModifiedOn(null);
		}
		for (TeamPlayer tp : team.getTeamPlayers()) {
			tp.setPlayerId(tp.getPlayer().getId());
		}
		team.setUserId(team.getUser().getId());
		return teamRepository.save(team);
	}

	@Override
	public List<Team> findAll() {
		List<Team> teams = (List<Team>) teamRepository.findAll();
		for (Team team : teams) {
			setTeamData(team);
		}
		return teams;
	}

	@Override
	public Team findById(Long id) {
		Team team = teamRepository.findById(id).get();
		setTeamData(team);
		return team;
	}

	@Override
	public Team findByUserId(Long userId) {
		Team team = teamRepository.findByUserId(userId);
		setTeamData(team);
		return team;
	}
	
	@Override
	public List<Team> findByLeagueId(Long leagueId) {
		List<Team> teams = new ArrayList<>();
		List<TeamLeagueMembership> memberships = tlmService.findByLeagueId(leagueId);
		for (TeamLeagueMembership tlm : memberships) {
			Team team = findById(tlm.getTeamId());
			setTeamData(team);
			teams.add(team);
		}
		return teams.stream().sorted(Comparator.comparingInt(Team::getTotalPoints).reversed()).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		teamRepository.deleteById(id);
	}

	@Override
	public void deleteByUserId(Long userId) {
		teamRepository.deleteByUserId(userId);
	}
	
	@Override
	public Long findIdByUserId(Long userId) {
		return teamRepository.findIdByUserId(userId);
	}
	
	// Private helper methods

	private boolean validateTeam(Team team) {
		List<TeamPlayer> players = team.getTeamPlayers();

		if (!validateTeamSize(players))
			throw new TeamException(TEAM_NOT_VALID, "Invalid team size!");

		if (!validateTeamDistinctPlayers(players))
			throw new TeamException(TEAM_NOT_VALID, "Team contains duplicate players!");

		if (!validateTeamPositions(players))
			throw new TeamException(TEAM_NOT_VALID, "Invalid player positions!");
		
		if (!validateBenchSize(players))
			throw new TeamException(TEAM_NOT_VALID, "Invalid bench size!");

		if (!validateTeamClubsLimit(players))
			throw new TeamException(TEAM_NOT_VALID, "You can select up to 3 players from a single club!");
		
		//TODO: AKO SE PRVI PUT PRAVI TIM, PROVERITI DA LI KOSTA ISPOD 100

		return true;
	}

	private boolean validateTeamSize(List<TeamPlayer> players) {
		if (players.size() != 15) {
			return false;
		}
		return true;
	}

	private boolean validateTeamDistinctPlayers(List<TeamPlayer> players) {
		List<TeamPlayer> distinctPlayers = players.stream().filter(distinctByKey(p -> p.getPlayer().getId()))
				.collect(Collectors.toList());
		if (distinctPlayers.size() != 15) {
			return false;
		}
		return true;
	}

	private boolean validateTeamPositions(List<TeamPlayer> players) {
		List<TeamPlayer> gk = players.stream().filter(p -> p.getPlayer().getPosition().equals("GK"))
				.collect(Collectors.toList());
		List<TeamPlayer> df = players.stream().filter(p -> p.getPlayer().getPosition().equals("DF"))
				.collect(Collectors.toList());
		List<TeamPlayer> mf = players.stream().filter(p -> p.getPlayer().getPosition().equals("MF"))
				.collect(Collectors.toList());
		List<TeamPlayer> fw = players.stream().filter(p -> p.getPlayer().getPosition().equals("FW"))
				.collect(Collectors.toList());

		if (gk.size() != 2 || df.size() != 5 || mf.size() != 5 || fw.size() != 3) {
			return false;
		}
		return true;
	}

	private boolean validateBenchSize(List<TeamPlayer> players) {
		long gkOnBench = players.stream().filter(p -> p.isOnBench() && p.getPlayer().getPosition().equals("GK")).count();
		long dfOnBench = players.stream().filter(p -> p.isOnBench() && p.getPlayer().getPosition().equals("DF")).count();
		long mfOnBench = players.stream().filter(p -> p.isOnBench() && p.getPlayer().getPosition().equals("MF")).count();
		long fwOnBench = players.stream().filter(p -> p.isOnBench() && p.getPlayer().getPosition().equals("FW")).count();

		if (gkOnBench != 1 || dfOnBench != 1 || mfOnBench != 1 || fwOnBench != 1) {
			return false;
		}
		return true;
	}

	private boolean validateTeamClubsLimit(List<TeamPlayer> players) {
		List<Club> distinctClubs = players.stream().filter(distinctByKey(p -> p.getPlayer().getClub().getId()))
				.map(p -> p.getPlayer().getClub()).collect(Collectors.toList());

		for (Club club : distinctClubs) {
			long clubCount = players.stream().filter(p -> p.getPlayer().getClub().getId() == club.getId()).count();
			if (clubCount > 3) {
				return false;
			}
		}
		return true;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	private void setTeamData(Team team) {
		setPlayers(team);
		setUser(team);
		setGameweeks(team);
	}
	
	private void setPlayers(Team team) {
		for (TeamPlayer tp : team.getTeamPlayers()) {
			Player player = footballFantasyClient.findPlayerById(tp.getPlayerId());
			tp.setPlayer(player);
		}
	}
	
	private void setUser(Team team) {
		User user = footballFantasyUserClient.findById(team.getUserId());
		team.setUser(user);
	}
	
	private void setGameweeks(Team team) {
		List<TeamGameweekPerformance> performances = team.getTeamGameweekPerformances();
		for (TeamGameweekPerformance tgp : performances) {
			Gameweek gameweek = footballFantasyClient.findGameweekById(tgp.getGameweekId());
			tgp.setGameweek(gameweek);
		}
	}

}
