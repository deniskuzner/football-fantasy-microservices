package com.fon.footballfantasyservice.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("football-fantasy-user-service")
public interface UserClient {
	
	@GetMapping(value = "/users/favourite-club/{id}")
	@ResponseBody Long findFavouriteClubByUserId(@PathVariable("id") Long userId);

}
