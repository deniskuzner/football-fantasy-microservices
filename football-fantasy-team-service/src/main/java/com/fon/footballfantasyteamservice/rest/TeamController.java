package com.fon.footballfantasyteamservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fon.footballfantasyteamservice.domain.Team;
import com.fon.footballfantasyteamservice.service.TeamService;
import com.fon.monitoring.annotation.Monitoring;
import com.fon.monitoring.annotation.Monitoring.Architecture;
import com.fon.monitoring.annotation.Monitoring.VirtualMachine;

@RestController
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	TeamService teamService;

	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 100)
	@PostMapping(value = "/teamSmall")
	ResponseEntity<?> saveSmall(@RequestBody Team team) {
		return new ResponseEntity<>(teamService.save(team), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 1000)
	@PostMapping(value = "/teamMedium")
	ResponseEntity<?> saveMedium(@RequestBody Team team) {
		return new ResponseEntity<>(teamService.save(team), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 10000)
	@PostMapping(value = "/teamLarge")
	ResponseEntity<?> saveLarge(@RequestBody Team team) {
		return new ResponseEntity<>(teamService.save(team), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	ResponseEntity<?> findAll() {
		return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
	}

	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 100)
	@GetMapping(value = "/teamSmall/{id}")
	ResponseEntity<?> findByIdSmall(@PathVariable("id") Long id) {
		return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 1000)
	@GetMapping(value = "/teamMedium/{id}")
	ResponseEntity<?> findByIdMedium(@PathVariable("id") Long id) {
		return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 10000)
	@GetMapping(value = "/teamLarge/{id}")
	ResponseEntity<?> findByIdLarge(@PathVariable("id") Long id) {
		return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{id}")
	ResponseEntity<?> findByUserId(@PathVariable("id") Long userId) {
		return new ResponseEntity<>(teamService.findByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/league/{id}")
	ResponseEntity<?> findByLeagueId(@PathVariable("id") Long leagueId) {
		return new ResponseEntity<>(teamService.findByLeagueId(leagueId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/team-id/user/{id}")
	ResponseEntity<?> findIdByUserId(@PathVariable("id") Long userId) {
		return new ResponseEntity<>(teamService.findIdByUserId(userId), HttpStatus.OK);
	}
	
}
