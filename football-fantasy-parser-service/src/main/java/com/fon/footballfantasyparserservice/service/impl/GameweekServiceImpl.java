package com.fon.footballfantasyparserservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyparserservice.domain.Gameweek;
import com.fon.footballfantasyparserservice.parser.FixturesPageHtmlParser;
import com.fon.footballfantasyparserservice.service.GameweekService;

@Service
@Validated
public class GameweekServiceImpl implements GameweekService {
	
	@Autowired
	FixturesPageHtmlParser fixturesParser;

	@Override
	public List<Gameweek> parseSeasonGameweeks() {
		return fixturesParser.parse();
	}

}
