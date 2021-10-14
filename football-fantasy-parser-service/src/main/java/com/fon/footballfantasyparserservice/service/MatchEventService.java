package com.fon.footballfantasyparserservice.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fon.footballfantasyparserservice.domain.event.MatchEvent;

public interface MatchEventService {

	List<MatchEvent> parseMatchEventsByMatchUrl(@NotNull String matchUrl);	

}
