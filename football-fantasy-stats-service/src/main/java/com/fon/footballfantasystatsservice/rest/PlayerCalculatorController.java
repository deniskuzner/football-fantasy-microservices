package com.fon.footballfantasystatsservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasystatsservice.domain.Player;
import com.fon.footballfantasystatsservice.domain.PlayerGameweekPerformance;
import com.fon.footballfantasystatsservice.service.PlayerCalculatorService;

@RestController
@RequestMapping("/player")
public class PlayerCalculatorController {

	@Autowired
	PlayerCalculatorService playerCalculatorService;
	
	@PostMapping(value = "/base-price")
	ResponseEntity<?> getBasePrice(@RequestBody Player player) {
		return new ResponseEntity<>(playerCalculatorService.getBasePrice(player), HttpStatus.OK);
	}

	@PostMapping(value = "/updated-price")
	ResponseEntity<?> getUpdatedPrice(@RequestBody PlayerGameweekPerformance pgp) {
		return new ResponseEntity<>(playerCalculatorService.getUpdatedPrice(pgp), HttpStatus.OK);
	}
	
}
