package com.fon.footballfantasyuserservice.rest;

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

import com.fon.footballfantasyuserservice.domain.User;
import com.fon.footballfantasyuserservice.service.UserService;
import com.fon.footballfantasyuserservice.service.dto.LoginCredentials;
import com.fon.monitoring.annotation.Monitoring;
import com.fon.monitoring.annotation.Monitoring.Architecture;
import com.fon.monitoring.annotation.Monitoring.VirtualMachine;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/login")
	ResponseEntity<?> login(@RequestBody LoginCredentials credentials) {
		return new ResponseEntity<>(userService.login(credentials), HttpStatus.OK);
	}

	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 100)
	@PostMapping(value = "/registerSmall")
	ResponseEntity<?> registerSmall(@RequestBody User user) {
		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 1000)
	@PostMapping(value = "/registerMedium")
	ResponseEntity<?> registerMedium(@RequestBody User user) {
		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}
	
	@Monitoring(architecture = Architecture.MICROSERVICE, virtualMachine = VirtualMachine.JVM, load = 10000)
	@PostMapping(value = "/registerLarge")
	ResponseEntity<?> registerLarge(@RequestBody User user) {
		return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
	}

	@PostMapping(value = "/user")
	ResponseEntity<?> save(@RequestBody User user) {
		return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
	}

	@GetMapping(value = "/user/{id}")
	ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	ResponseEntity<?> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/{id}")
	ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/favourite-club/{id}")
	ResponseEntity<?> findFavouriteClubByUserId(@PathVariable("id") Long userId) {
		return new ResponseEntity<>(userService.findFavouriteClubByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/username")
	ResponseEntity<?> findByUsername(@RequestBody String username) {
		return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
	}

}
