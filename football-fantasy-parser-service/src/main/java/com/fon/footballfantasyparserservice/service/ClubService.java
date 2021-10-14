package com.fon.footballfantasyparserservice.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fon.footballfantasyparserservice.domain.Club;

public interface ClubService {
	
	List<Club> parseSeasonClubs();
	
	Club parseClubByUrl(@NotNull String url);
	
}
