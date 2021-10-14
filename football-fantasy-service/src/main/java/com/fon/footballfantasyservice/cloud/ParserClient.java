package com.fon.footballfantasyservice.cloud;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fon.footballfantasyservice.domain.Club;
import com.fon.footballfantasyservice.domain.Gameweek;
import com.fon.footballfantasyservice.domain.event.MatchEvent;

@FeignClient("football-fantasy-parser-service")
public interface ParserClient {
	
	@GetMapping("/parser/clubs/parse-season-clubs")
	@ResponseBody List<Club> parseSeasonClubs();
	
	@PostMapping(value = "/parser/clubs/parse")
	@ResponseBody Club parseClub(String url);
	
	@GetMapping(value = "/parser/gameweeks/parse-season-gameweeks")
	@ResponseBody List<Gameweek> parseSeasonGameweeks();
	
	@PostMapping(value = "/parser/match-events/parse-match-events/match")
	@ResponseBody List<MatchEvent> parseMatchEventsByMatchUrl(String matchUrl);
	
}
