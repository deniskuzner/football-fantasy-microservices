package com.fon.footballfantasyteamservice.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.footballfantasyteamservice.domain.dto.Gameweek;
import com.fon.footballfantasyteamservice.domain.dto.Player;

@FeignClient("football-fantasy-service")
public interface FootballFantasyClient {

	@GetMapping(value = "/gameweeks/gameweek-id/order-number/{orderNumber}")
	@ResponseBody Long findIdByOrderNumber(@PathVariable("orderNumber") int orderNumber);
	
	@GetMapping(value = "/performances/count/gameweek/{id}")
	@ResponseBody Long countByGameweekId(@PathVariable("id") Long gameweekId);
	
	@GetMapping(value = "/players/player/{id}")
	@ResponseBody Player findPlayerById(@PathVariable("id") Long id);
	
	@GetMapping(value = "/gameweeks/gameweek/{id}")
	@ResponseBody Gameweek findGameweekById(@PathVariable("id") Long id);

}
