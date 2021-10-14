package com.fon.footballfantasyteamservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyteamservice.domain.League;
import com.fon.footballfantasyteamservice.domain.TeamLeagueMembership;
import com.fon.footballfantasyteamservice.repository.LeagueRepository;
import com.fon.footballfantasyteamservice.service.LeagueService;
import com.fon.footballfantasyteamservice.service.TeamLeagueMembershipService;

@Service
@Transactional
@Validated
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	LeagueRepository leagueRepository;
	
	@Autowired
	TeamLeagueMembershipService tlmService;

	@Override
	public League save(League league) {
		return leagueRepository.save(league);
	}

	@Override
	public List<League> findAll() {
		return (List<League>) leagueRepository.findAll();
	}

	@Override
	public League findById(Long id) {
		return leagueRepository.findById(id).get();
	}

	@Override
	public List<League> findByTeamId(Long teamId) {
		List<League> teamLeagues = new ArrayList<>();
		List<TeamLeagueMembership> memberships = tlmService.findByTeamId(teamId);
		for (TeamLeagueMembership tlm : memberships) {
			teamLeagues.add(findById(tlm.getLeagueId()));
		}
		return teamLeagues;
	}

	@Override
	public void deleteById(Long id) {
		tlmService.deleteByLeagueId(id);
		leagueRepository.deleteById(id);
	}
	
	
}
