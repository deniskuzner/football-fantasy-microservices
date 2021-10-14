package com.fon.footballfantasyparserservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasyparserservice.service.ClubService;

@RestController
@RequestMapping("/clubs")
public class ClubController {

	@Autowired
	ClubService clubService;

	@GetMapping(value = "/parse-season-clubs")
	ResponseEntity<?> parseSeasonClubs() {
		return new ResponseEntity<>(clubService.parseSeasonClubs(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/parse")
	ResponseEntity<?> parseClub(@RequestBody String url) {
		return new ResponseEntity<>(clubService.parseClubByUrl(url), HttpStatus.OK);
	}

}
