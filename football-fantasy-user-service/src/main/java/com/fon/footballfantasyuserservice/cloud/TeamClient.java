package com.fon.footballfantasyuserservice.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("football-fantasy-team-service")
public interface TeamClient {

	@GetMapping(value = "/teams/team-id/user/{id}")
	@ResponseBody Long findIdByUserId(@PathVariable("id") Long userId);
	
}
