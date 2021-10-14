package com.fon.footballfantasyteamservice.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.footballfantasyteamservice.domain.dto.User;

@FeignClient("football-fantasy-user-service")
public interface FootballFantasyUserClient {

	@GetMapping(value = "/users/user/{id}")
	@ResponseBody User findById(@PathVariable("id") Long id);
	
}
