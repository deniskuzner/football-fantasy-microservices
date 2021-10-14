package com.fon.footballfantasyservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasyservice.domain.Gameweek;
import com.fon.footballfantasyservice.service.GameweekService;

@RestController
@RequestMapping("/gameweeks")
public class GameweekController {

	@Autowired
	GameweekService gameweekService;
	
	@GetMapping(value = "/parse-season-gameweeks")
	ResponseEntity<?> parseSeasonGameweeks() {
		return new ResponseEntity<>(gameweekService.parseSeasonGameweeks(), HttpStatus.OK);
	}

	@PostMapping(value = "/gameweek")
	ResponseEntity<?> save(@RequestBody Gameweek gameweek) {
		return new ResponseEntity<>(gameweekService.save(gameweek), HttpStatus.OK);
	}

	@GetMapping(value = "/gameweek/{id}")
	ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(gameweekService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/gameweek/order-number/{orderNumber}")
	ResponseEntity<?> findByOrderNumber(@PathVariable("orderNumber") int orderNumber) {
		return new ResponseEntity<>(gameweekService.findByOrderNumber(orderNumber), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	ResponseEntity<?> findAll() {
		return new ResponseEntity<>(gameweekService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/gameweek/{id}")
	ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		gameweekService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/count")
	ResponseEntity<?> count() {
		return new ResponseEntity<>(gameweekService.count(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/current")
	ResponseEntity<?> findCurrentGameweek() {
		return new ResponseEntity<>(gameweekService.findCurrentGameweek(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/current/order-number")
	ResponseEntity<?> findCurrentGameweekOrderNumber() {
		return new ResponseEntity<>(gameweekService.findCurrentGameweekOrderNumber(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/gameweek-id/order-number/{orderNumber}")
	ResponseEntity<?> findIdByOrderNumber(@PathVariable("orderNumber") int orderNumber) {
		return new ResponseEntity<>(gameweekService.findIdByOrderNumber(orderNumber), HttpStatus.OK);
	}

}
