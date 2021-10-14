package com.fon.footballfantasystatsservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasystatsservice.domain.Match;
import com.fon.footballfantasystatsservice.service.MatchCalculatorService;

@RestController
@RequestMapping("/match")
public class MatchCalculatorController {

	@Autowired
	MatchCalculatorService matchCalculatorService;
	
	@PostMapping(value = "/performances")
	ResponseEntity<?> getMatchPerformances(@RequestBody Match match) {
		return new ResponseEntity<>(matchCalculatorService.getMatchPerformances(match), HttpStatus.OK);
	}
	
}
