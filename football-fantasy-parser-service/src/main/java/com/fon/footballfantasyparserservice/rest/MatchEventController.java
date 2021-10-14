package com.fon.footballfantasyparserservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasyparserservice.service.MatchEventService;

@RestController
@RequestMapping("/match-events")
public class MatchEventController {

	@Autowired
	MatchEventService matchEventService;

	@PostMapping(value = "/parse-match-events/match")
	ResponseEntity<?> parseMatchEventsByMatchUrl(@RequestBody String matchUrl) {
		return new ResponseEntity<>(matchEventService.parseMatchEventsByMatchUrl(matchUrl), HttpStatus.OK);
	}

}
