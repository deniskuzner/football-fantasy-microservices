package com.fon.footballfantasyparserservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fon.footballfantasyparserservice.domain.event.MatchEvent;
import com.fon.footballfantasyparserservice.parser.MatchPageHtmlParser;
import com.fon.footballfantasyparserservice.service.MatchEventService;

@Service
@Validated
public class MatchEventServiceImpl implements MatchEventService {
	
	@Autowired
	MatchPageHtmlParser matchPageHtmlParser;

	@Override
	public List<MatchEvent> parseMatchEventsByMatchUrl(String matchUrl) {
		return matchPageHtmlParser.parse(matchUrl);
	}

}
