package com.fon.footballfantasyservice.cloud;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.footballfantasyservice.domain.Match;
import com.fon.footballfantasyservice.domain.Player;
import com.fon.footballfantasyservice.domain.PlayerGameweekPerformance;

@FeignClient("football-fantasy-stats-service")
public interface StatsClient {
	
	@PostMapping(value = "/stats/match/performances")
	@ResponseBody List<PlayerGameweekPerformance> getMatchPerformances(Match match);
	
	@PostMapping(value = "/stats/player/base-price")
	@ResponseBody double getBasePrice(Player player);
	
	@PostMapping(value = "/stats/player/updated-price")
	@ResponseBody double getUpdatedPrice(PlayerGameweekPerformance pgp);

}
