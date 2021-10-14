package com.fon.footballfantasyparserservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasyparserservice.service.GameweekService;

@RestController
@RequestMapping("/gameweeks")
public class GameweekController {

	@Autowired
	GameweekService gameweekService;

	@GetMapping(value = "/parse-season-gameweeks")
	ResponseEntity<?> parseSeasonGameweeks() {
		return new ResponseEntity<>(gameweekService.parseSeasonGameweeks(), HttpStatus.OK);
	}

}
