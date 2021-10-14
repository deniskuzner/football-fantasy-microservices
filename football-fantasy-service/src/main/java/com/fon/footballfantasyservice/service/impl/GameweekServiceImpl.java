package com.fon.footballfantasyservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyservice.cloud.ParserClient;
import com.fon.footballfantasyservice.domain.Gameweek;
import com.fon.footballfantasyservice.repository.GameweekRepository;
import com.fon.footballfantasyservice.service.GameweekService;
import com.fon.footballfantasyservice.service.MatchService;

@Service
@Transactional
@Validated
public class GameweekServiceImpl implements GameweekService {
	
	@Autowired
	GameweekRepository gameweekRepository;
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	ParserClient parserClient;

	@Override
	public List<Gameweek> parseSeasonGameweeks() {
		List<Gameweek> gameweeks = parserClient.parseSeasonGameweeks();
		gameweeks.forEach(gw -> save(gw));
		return gameweeks;
	}

	@Override
	public Gameweek save(Gameweek gameweek) {
		// Provera da li vec postoji u bazi gameweek
		Gameweek g = gameweekRepository.findByOrderNumber(gameweek.getOrderNumber());
		if(g != null) {
			gameweek.setId(g.getId());
			gameweek.setCreatedOn(g.getCreatedOn());
		}
		gameweekRepository.save(gameweek);
		gameweek.getMatches().forEach(m -> {
			m.setGameweek(gameweek);
			matchService.save(m);
		});
		return gameweek;
	}

	@Override
	public Gameweek findById(Long id) {
		return gameweekRepository.findById(id).get();
	}

	@Override
	public Gameweek findByOrderNumber(int orderNumber) {
		return gameweekRepository.findByOrderNumber(orderNumber);
	}

	@Override
	public List<Gameweek> findAll() {
		return (List<Gameweek>) gameweekRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		gameweekRepository.deleteById(id);
	}

	@Override
	public long count() {
		return gameweekRepository.count();
	}

	@Override
	public Gameweek findCurrentGameweek() {
		Long id = gameweekRepository.findCurrentGameweekId();
		if(id == null) {
			return null;
		}
		return gameweekRepository.findById(id).get();
	}

	@Override
	public int findCurrentGameweekOrderNumber() {
		Long id = gameweekRepository.findCurrentGameweekId();
		if(id == null) {
			return -1;
		}
		return gameweekRepository.findOrderNumberById(id);
	}

	@Override
	public Long findIdByOrderNumber(int orderNumber) {
		return gameweekRepository.findIdByOrderNumber(orderNumber);
	}

}
