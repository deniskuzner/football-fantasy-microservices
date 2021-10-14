package com.fon.footballfantasyparserservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyparserservice.domain.Club;
import com.fon.footballfantasyparserservice.parser.ClubPageHtmlParser;
import com.fon.footballfantasyparserservice.parser.SeasonClubPageHtmlParser;
import com.fon.footballfantasyparserservice.service.ClubService;

@Service
@Validated
public class ClubServiceImpl implements ClubService {
	
	@Autowired
	SeasonClubPageHtmlParser seasonClubParser;
	
	@Autowired
	ClubPageHtmlParser clubParser;
	
	@Override
	public List<Club> parseSeasonClubs() {
		return seasonClubParser.getSeasonClubs();
	}
	
	@Override
	public Club parseClubByUrl(String url) {
		return clubParser.parse(url);
	}

}
