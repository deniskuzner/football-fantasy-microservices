package com.fon.footballfantasyservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyservice.cloud.ParserClient;
import com.fon.footballfantasyservice.cloud.UserClient;
import com.fon.footballfantasyservice.domain.Club;
import com.fon.footballfantasyservice.domain.Player;
import com.fon.footballfantasyservice.repository.ClubRepository;
import com.fon.footballfantasyservice.service.ClubService;
import com.fon.footballfantasyservice.service.PlayerService;

@Service
@Transactional
@Validated
public class ClubServiceImpl implements ClubService {
	
	@Autowired
	ParserClient parserClient;
	
	@Autowired
	ClubRepository clubRepository;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	UserClient userClient;

	@Override
	public List<Club> parseSeasonClubs() {
		List<Club> clubs = parserClient.parseSeasonClubs();
		for (Club club : clubs) {
			save(club);
		}
		
		return clubs;
	}
	
	@Override
	public Club parseClubByUrl(String url) {
		Club club = parserClient.parseClub(url);
		save(club);
		return club;
	}

	@Override
	public Club save(Club club) {
		Club c = clubRepository.findByUrl(club.getUrl());
		if(c != null) {
			club.setId(c.getId());
			club.setCreatedOn(c.getCreatedOn());
		}
		
		clubRepository.save(club);
		
		for (Player player : club.getPlayers()) {
			player.setClub(club);
			playerService.save(player);
		}
		
		return club;
	}

	@Override
	public Club findById(Long id) {
		return clubRepository.findById(id).get();
	}
	
	@Override
	public Club findByUrl(String url) {
		return clubRepository.findByUrl(url);
	}

	@Override
	public List<Club> findAll() {
		return (List<Club>) clubRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		clubRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		clubRepository.deleteAll();
	}

	@Override
	public List<String> findAllNames() {
		return clubRepository.findAllNames();
	}

	@Override
	public Club findFavouriteClub(Long userId) {
		Long favouriteClubId = userClient.findFavouriteClubByUserId(userId);
		return clubRepository.findById(favouriteClubId).get();
	}

}
