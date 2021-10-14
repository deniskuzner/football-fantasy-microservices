package com.fon.footballfantasyparserservice.service;

import java.util.List;

import com.fon.footballfantasyparserservice.domain.Gameweek;

public interface GameweekService {

	List<Gameweek> parseSeasonGameweeks();

}
